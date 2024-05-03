package qcm.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import qcm.models.Utilisateur;
import qcm.services.ReponseCode;
import qcm.services.UtilisateurService;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        this.service=new UtilisateurService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	String dateNaissanceStr = request.getParameter("datenaiss");
	    	if(!request.getParameter("repassword").equals(request.getParameter("password"))) {
	    		request.setAttribute("error", "Vous devez resaisir le meme mot de passe !");
	            request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
	    	}else if(dateNaissanceStr != null && !dateNaissanceStr.isEmpty()){
	    		// Convertir la date de naissance en objet LocalDate
	    	    LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr);

	    	    // Vérifier si la personne est née après 1980
	    	    if (dateNaissance.getYear() <= 1980) {
	    	        request.setAttribute("error", "La date de naissance doit être postérieure à 1980.");
	    	        request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
	    	    } else {
	    	        // Vérifier si la personne a au moins 10 ans
	    	        LocalDate dateLimite = LocalDate.now().minusYears(10);
	    	        if (dateNaissance.isAfter(dateLimite)) {
	    	            request.setAttribute("error", "Vous devez avoir au moins 10 ans pour vous inscrire.");
	    	            request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
	    	        } else {
	    	            // La date de naissance est valide, poursuivre le traitement
	    	        	// Crée un utilisateur à partir des données de la requête
	    		        Utilisateur user = new Utilisateur(request);
	    	
	    		        ReponseCode res= service.createAccount(user);
	    		        if (res.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
	    		            // L'utilisateur a été ajouté avec succès
	    		         // Redirige vers la page de connexion
	    		        	response.sendRedirect(request.getContextPath() + "/Login");
	    		        }else {
	    		            // Erreur lors de l'ajout de l'utilisateur
	    		            request.setAttribute("error", res.getErrorMessage());
	    		            request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
	    		        }
	    	        }
	    	    }
	    		
	    	}else {
		        request.setAttribute("error", "Date de naissance invalide !");
	            request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);

	    	}
	    } catch (Exception e) {
	        e.printStackTrace();
	     // Erreur lors de la connexion à l'API, rediriger vers la page d'erreur avec l'exception
	        request.setAttribute("javax.servlet.error.exception", e);
	        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);

	    }
	}

	
	
	
}
