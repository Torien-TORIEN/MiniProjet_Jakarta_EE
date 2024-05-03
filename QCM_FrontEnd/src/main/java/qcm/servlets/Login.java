package qcm.servlets;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





import qcm.models.Utilisateur;
import qcm.services.ReponseCode;
import qcm.services.UtilisateurService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        this.service=new UtilisateurService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        // Récupérer les paramètres de la requête
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        
	        Map<String, Object> loginResult= service.login(email, password);
	        ReponseCode res = (ReponseCode)loginResult.get("responseCode");
	        Utilisateur utilisateur = (Utilisateur)loginResult.get("user");
	        
	        if (res.getResponseCode() == HttpURLConnection.HTTP_OK) {
	            // Connexion réussie

	            // Stocker l'utilisateur dans la session
	            HttpSession session = request.getSession();
	            session.setAttribute("user", utilisateur);

	            // Rediriger vers la page d'accueil
	            response.sendRedirect(request.getContextPath() + "/Home/test");
	            
	        }else {
	            // Connexion échouée, rediriger vers la page de connexion avec un message d'erreur
	            request.setAttribute("error", res.getErrorMessage());
	            request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	        }
	    } catch (Exception e) {
	        //e.printStackTrace();
	        // Erreur lors de la connexion à l'API, rediriger vers la page de connexion avec un message d'erreur
	        /*request.setAttribute("error", "An error occurred while processing your request");
	        request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);*/
	        
	        // Erreur lors de la connexion à l'API, rediriger vers la page d'erreur avec l'exception
	        request.setAttribute("javax.servlet.error.exception", e);
	        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
	    }
	}

}
