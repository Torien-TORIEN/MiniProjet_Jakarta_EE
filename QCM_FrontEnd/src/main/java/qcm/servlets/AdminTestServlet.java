package qcm.servlets;

import qcm.models.Question;
import qcm.models.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


import org.json.simple.JSONObject;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class AdminTestServlet
 */
@WebServlet("/Admin")
public class AdminTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID du test sélectionné dans le formulaire
        String idTestString = request.getParameter("id_test");
        
        // Vérifier si l'ID du test a été sélectionné
        if (idTestString != null && !idTestString.isEmpty()) {
            try {
                // Convertir l'ID du test en entier
                int idTest = Integer.parseInt(idTestString);
                
                // Faire quelque chose avec l'ID du test (par exemple, l'imprimer pour le tester)
                System.out.println("ID du test sélectionné : " + idTest);
                
                // Appel à l'API pour récupérer les questions associées à ce test
                String apiUrl = "http://localhost:8080/TestAPI/api/tests/" + idTest + "/questions";
                URL url = new URL(apiUrl);
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
                ArrayList<Question> questions = objectMapper.readValue(jsonResponse.toString(),
                        objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Question.class));
                
                // Mettre les questions dans la session
                request.getSession().setAttribute("adm_questions", questions);

                
                // Rediriger vers une page où vous afficherez les questions (par exemple, la page Admin.jsp)
                request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
                
            } catch (NumberFormatException e) {
                // Gérer l'erreur si l'ID du test n'est pas un entier valide
                System.out.println("L'ID du test n'est pas un entier valide : " + idTestString);
                request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
            } catch (Exception e) {
            	System.out.println("Erreur dans le servlet Admin :"+ e.getMessage());
            	e.printStackTrace();
            	request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
            }
        } else {
            // Si aucun ID de test n'est sélectionné, vous pouvez gérer cela en conséquence
            
            // Par exemple, vous pouvez rediriger l'utilisateur vers une page d'erreur ou afficher un message d'erreur
            System.out.println("Aucun ID de test sélectionné !");
            HttpSession session = request.getSession(false);
            session.removeAttribute("adm_questions");
            request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer les données de la requête pour créer un objet Question
            Question question = new Question(request);

            // Récupérer l'ID du test sélectionné dans le formulaire
            int idTest = Integer.parseInt(request.getParameter("test"));
            
            //Recuperer le test:
            HttpSession session = request.getSession(false);
            ArrayList<Test> tests = (ArrayList<Test>)session.getAttribute("tests");
            Test test = new Test();
            test.setId(idTest);
            for(Test t :tests) {
                if(t.getId()==idTest)
                    test=t;
            }
            question.setTest(test);
            
            System.out.println("Test :\n"+test);
            System.out.println("Question :\n"+question);

            // Créer un objet JSONObject pour représenter la question
            JSONObject questionJson = new JSONObject();
            questionJson.put("id", question.getId());
            questionJson.put("question", question.getQuestion());
            questionJson.put("reponse", question.getReponse());
            questionJson.put("rep1", question.getRep1());
            questionJson.put("rep2", question.getRep2());
            questionJson.put("rep3", question.getRep3());

            // Convertir l'objet JSONObject en chaîne JSON
            String jsonString = questionJson.toString();
            System.out.println(jsonString);

            // Appeler l'API pour ajouter la question au test spécifié
            String apiUrl = "http://localhost:8080/TestAPI/api/questions?id_test="+idTest;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(jsonString.getBytes());
            os.flush();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Question ajoutée avec succès !");
                request.setAttribute("success", "La question est ajoutée au test <<"+test.getCategorie()+">> avec succès !");
                request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
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
                request.setAttribute("error", "Erreur serveur : " + errorMessage.toString());
                // Rediriger vers la page JSP appropriée
                request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
            	
            }else {
                System.out.println("Erreur lors de l'ajout de la question !" + responseCode);
                request.setAttribute("error", "Erreur d'ajout, Vérifiez si le serveur est en marche !");
                request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur : "+e.getMessage());
            request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
        }
    }

}
