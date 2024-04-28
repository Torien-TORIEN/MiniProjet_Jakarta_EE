package qcm.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import qcm.models.Utilisateur;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
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

	        // Créer les paramètres de la requête HTTP
	        String apiUrl = "http://localhost:8080/TestAPI/api/users/login";
	        String params = "email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");

	        // Créer la connexion HTTP
	        URL url = new URL(apiUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        connection.setDoOutput(true);

	        // Envoyer les paramètres de la requête
	        try (OutputStream os = connection.getOutputStream()) {
	            byte[] input = params.getBytes("utf-8");
	            os.write(input, 0, input.length);
	        }

	        // Lire la réponse de l'API
	        int responseCode = connection.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            // Connexion réussie
	            System.out.println("Logged in successfully");

	            // Lire la réponse JSON
	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String inputLine;
	            StringBuffer responseBuffer = new StringBuffer();
	            while ((inputLine = in.readLine()) != null) {
	                responseBuffer.append(inputLine);
	            }
	            in.close();

	            // Convertir la réponse JSON en objet Utilisateur
	            ObjectMapper mapper = new ObjectMapper();
	            Utilisateur utilisateur = mapper.readValue(responseBuffer.toString(), Utilisateur.class);

	            // Stocker l'utilisateur dans la session
	            HttpSession session = request.getSession();
	            session.setAttribute("user", utilisateur);

	            // Rediriger vers la page d'accueil
	            response.sendRedirect(request.getContextPath() + "/Home/test");
	            
	        } else if(responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
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
                request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
            	
            }else {
	            // Connexion échouée, rediriger vers la page de connexion avec un message d'erreur
	            request.setAttribute("error", "Incorrect email or password");
	            request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        // Erreur lors de la connexion à l'API, rediriger vers la page de connexion avec un message d'erreur
	        request.setAttribute("error", "An error occurred while processing your request");
	        request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	    }
	}

}
