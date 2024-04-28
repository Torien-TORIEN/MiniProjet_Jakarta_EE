package qcm.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import qcm.models.Utilisateur;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        // Crée un utilisateur à partir des données de la requête
	        Utilisateur user = new Utilisateur(request);

	        // Appelle l'API pour ajouter l'utilisateur
	        String apiUrl = "http://localhost:8080/TestAPI/api/users";
	        URL url = new URL(apiUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/json");
	        connection.setDoOutput(true);

	        ObjectMapper mapper = new ObjectMapper();
	        String userJson = mapper.writeValueAsString(user);

	        OutputStream os = connection.getOutputStream();
	        os.write(userJson.getBytes());
	        os.flush();

	        int responseCode = connection.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_CREATED) {
	            // L'utilisateur a été ajouté avec succès
	            System.out.println("Utilisateur ajouté avec succès !");
	         // Redirige vers la page de connexion
		        request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	        }else if(responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
            	// Lire le corps de la réponse pour récupérer le message d'erreur
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                StringBuilder errorMessage = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    errorMessage.append(line);
                }
                reader.close();
                
                // Afficher le message d'erreur ou le stocker pour une utilisation ultérieure
                System.out.println("Erreur serveur : " + errorMessage.toString());
                // Ajoutez le message d'erreur à l'attribut "error" pour le transmettre à la page JSP
                request.setAttribute("error", errorMessage.toString());
                // Rediriger vers la page JSP appropriée
                request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
            	
            } else {
	            // Erreur lors de l'ajout de l'utilisateur
	            request.setAttribute("error", "Votre compte n'a pu être créé !");
	            request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
	        }

	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Redirige vers la page d'inscription avec un message d'erreur
	        request.setAttribute("error", e.getMessage());
	        request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
	    }
	}

	
	
	
}
