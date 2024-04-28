package qcm.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import qcm.models.Question;
import qcm.models.Score;
import qcm.models.Test;
import qcm.models.Utilisateur;

/**
 * Servlet implementation class ScoreServlet
 */
@WebServlet("/ScoreServlet")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		try {
			Utilisateur user =(Utilisateur)session.getAttribute("user");
		
			ArrayList<Score> scores = null;
	        String apiUrl = "http://localhost:8080/TestAPI/api/scores/sort/user/"+user.getId();
	
	        try {
	            // Créer une URL pour l'API
	            URL url = new URL(apiUrl);
	
	            // Ouvrir une connexion HTTP avec l'API
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");
	
	            // Récupérer la réponse de l'API
	            InputStream inputStream = connection.getInputStream();
	            Scanner scanner = new Scanner(inputStream, "UTF-8");
	
	            // Lire la réponse JSON
	            StringBuilder jsonResponse = new StringBuilder();
	            while (scanner.hasNextLine()) {
	                jsonResponse.append(scanner.nextLine());
	            }
	
	            // Fermer les ressources
	            scanner.close();
	            inputStream.close();
	            connection.disconnect();
	
	            // Convertir la réponse JSON en une liste d'objets Test
	            ObjectMapper objectMapper = new ObjectMapper();
	            scores = objectMapper.readValue(jsonResponse.toString(),
	                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Score.class));
	            
	            for(Score sc :scores)
	            {
	            	System.out.println(sc);
	            }
	
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	
	        // Mettre les tests dans la session
	        request.getSession().setAttribute("scores", scores);
	
	        // Rediriger vers la page appropriée après avoir récupéré les tests
	        //request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	        response.sendRedirect(request.getContextPath() + "/Home");
		}catch(Exception e) {
			System.out.println("Failed to get user in session !");
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        // Lire le corps de la requête
	        BufferedReader reader = request.getReader();
	        StringBuilder requestBody = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            requestBody.append(line);
	        }
	        reader.close();
	
	        // Extraire les réponses de la chaîne du corps de la requête
	        String[] responsePairs = requestBody.toString().split("&");
	        List<String> responses = new ArrayList<>();
	        for (String pair : responsePairs) {
	            String[] keyValue = pair.split("=");
	            responses.add(keyValue[1]);
	        }
	
	        // Récupérer la session et les données pertinentes
	        HttpSession session = request.getSession(false);
	        ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
	        Utilisateur user = (Utilisateur) session.getAttribute("user");
	        Score score = (Score) session.getAttribute("score");
	
	        // Initialiser le score si nécessaire
	        if (score == null) {
	            score = new Score();
	            score.setScore(0.0);
	            score.setUser(user);
	            score.setTest(questions.get(0).getTest());
	        }
	
	        // Calculer le score
	        double sc = 0.0;
	        if (responses.size() == 1 && responses.contains("V")) {
	            sc = 1.0;
	            score.setScore(score.getScore() + sc);
	        }
	
	        // Mettre à jour la session avec le score
	        session.setAttribute("score", score);
	
	        // Traiter les scores si toutes les questions ont été répondues
	        int iQ = (Integer) session.getAttribute("iQ");
	        if (iQ + 1 < questions.size()) {
	            session.setAttribute("iQ", iQ + 1);
	            session.setAttribute("NQ", questions.get(iQ + 1).getId());
	        } else {
	            // Calculer le score final
	            score.setScore((score.getScore() / questions.size()) * 100);
	            
	            System.out.println("Le score : "+ score);
	
	            // Enregistrer le score dans la base de données
	            String apiUrl = "http://localhost:8080/TestAPI/api/scores";
	            URL url = new URL(apiUrl);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Content-Type", "application/json");
	            connection.setDoOutput(true);
	
	            // Convertir l'objet Score en JSON
	            ObjectMapper mapper = new ObjectMapper();
	            String scoreJson = mapper.writeValueAsString(score);
	
	            // Envoyer le JSON à l'API
	            OutputStream os = connection.getOutputStream();
	            os.write(scoreJson.getBytes());
	            os.flush();
	
	            // Vérifier le code de réponse
	            int responseCode = connection.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_CREATED) {
	                System.out.println("Score enregistré avec succès !");
	                doGet(request,response);
	            } else {
	                System.out.println("Erreur lors de l'enregistrement du score !");
	            }
	
	            // Supprimer les attributs de session
	            session.removeAttribute("questions");
	            session.removeAttribute("score");
	            session.removeAttribute("NQ");
	            session.removeAttribute("iQ");
	        }
	
	        // Rediriger vers la page d'accueil
	        response.sendRedirect(request.getContextPath() + "/Home");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
