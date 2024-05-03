package qcm.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import qcm.models.Question;
import qcm.models.Test;
import qcm.services.TestService;

import java.util.*;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestService service; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        this.service=new TestService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Test> tests = null;

        try {
            
        	tests=service.getAllTests();

        } catch (Exception e) {
            e.printStackTrace();
         // Erreur lors de la connexion à l'API, rediriger vers la page d'erreur avec l'exception
	        request.setAttribute("javax.servlet.error.exception", e);
	        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);

        }

        // Mettre les tests dans la session
        request.getSession().setAttribute("tests", tests);

        // Rediriger vers la page appropriée après avoir récupéré les tests
        response.sendRedirect(request.getContextPath() + "/Home");
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Récupérer l'ID du test à partir du corps de la requête
		
		System.out.println("Servlet : TestSerlet ");
	    BufferedReader reader = request.getReader();
	    StringBuilder requestBody = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        requestBody.append(line);
	    }
	    reader.close();

	    // Extraire l'ID du test du contenu du corps de la requête
	    String requestBodyString = requestBody.toString();
	    int testId = Integer.parseInt(requestBodyString.split("=")[1]);
	    //System.out.println("IdTest ="+testId);

	    // Faire une requête à l'API pour obtenir les questions aléatoires pour ce test
	    //String apiUrl = "http://localhost:8080/TestAPI/api/tests/" + testId + "/randomQuestions?count=5";
	    try {
	        /*// Créer une URL pour l'API
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

	        // Convertir la réponse JSON en une liste d'objets Question
	        ObjectMapper objectMapper = new ObjectMapper();
	        List<Question> questions = objectMapper.readValue(jsonResponse.toString(),
	                objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class));
			*/
	    	List<Question> questions = service.getRandomQuestionsByTest(testId, 10);
	        // Mettre les questions dans la session
	        request.getSession().setAttribute("questions", questions);
	        if(questions.size()>0) {
	        	
	        	//id  du premier element
	        	request.getSession().setAttribute("NQ",questions.get(0).getId());
	        	//indice de question actuelle
	        	request.getSession().setAttribute("iQ",0);
	        }
	        
	     // Supprimer l'attribut "tests" de la session
	        //request.getSession().removeAttribute("tests");
	     
	        
	        // Afficher les question
	        /*(Question q :questions) {
	        	System.out.println(q);
	        }*/

	        // Rediriger vers la page appropriée après avoir récupéré les questions
	        //request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	        response.sendRedirect(request.getContextPath() + "/Home");

	    } catch (Exception e) {
	        e.printStackTrace();
	     // Erreur lors de la connexion à l'API, rediriger vers la page d'erreur avec l'exception
	        request.setAttribute("javax.servlet.error.exception", e);
	        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);

	    }
	}


}
