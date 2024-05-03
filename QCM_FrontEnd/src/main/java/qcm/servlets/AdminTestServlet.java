package qcm.servlets;

import qcm.models.Question;
import qcm.models.Test;
import qcm.services.QuestionService;
import qcm.services.ReponseCode;

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
	private QuestionService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTestServlet() {
        super();
        this.service=new QuestionService();
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
                
                ArrayList<Question> questions = service.getAllQuestionsByIdTest(idTest);
                
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
            	//request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
            	// Erreur lors de la connexion à l'API, rediriger vers la page d'erreur avec l'exception
    	        request.setAttribute("javax.servlet.error.exception", e);
    	        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);

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
            ReponseCode res = service.addQuestion(question);
            
            if (res.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Question ajoutée avec succès !");
                request.setAttribute("success", res.getSuccessMessage());
                
    	        session.removeAttribute("adm_questions");
                
                request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
            }else{
                request.setAttribute("error", res.getErrorMessage());
                request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur : "+e.getMessage());
            //request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
         // Erreur lors de la connexion à l'API, rediriger vers la page d'erreur avec l'exception
	        request.setAttribute("javax.servlet.error.exception", e);
	        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);

        }
    }

}
