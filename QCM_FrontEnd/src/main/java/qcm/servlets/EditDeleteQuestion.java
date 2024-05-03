package qcm.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import qcm.models.Question;
import qcm.models.Test;
import qcm.services.QuestionService;
import qcm.services.ReponseCode;

/**
 * Servlet implementation class EditDeleteQuestion
 */
@WebServlet("/EditDeleteQuestion")
public class EditDeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QuestionService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteQuestion() {
        super();
        this.service= new QuestionService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérer l'ID du test sélectionné dans le formulaire
        String idQuestiontoEdit = request.getParameter("id_question_edit");
        String idQuestiontoDelete = request.getParameter("id_question_delete");
        HttpSession session = request.getSession(false);
        
     // Vérifier si l'ID du test a été sélectionné
        if (idQuestiontoEdit != null && !idQuestiontoEdit.isEmpty()) {
        	System.out.println("Edit Action :"+idQuestiontoEdit);
        	try {
        		// Convertir l'ID de la question en entier
        	    int idQuestion = Integer.parseInt(idQuestiontoEdit);
        	    
        	    // get Question in session
        	    ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("adm_questions");
        	    /*
        	    if(questions== null || questions.isEmpty())
        	    	throw new Exception(" No Question Found in Session adm_questions");
        	    */
        	    
        	    Question question_to_edit= null;
        	    
        	    for(Question q : questions) {
        	    	if(q.getId()== idQuestion) {
        	    		question_to_edit=q;
        	    		break;
        	    	}
        	    }
        	    
        	    /*if(question_to_edit == null)
        	    	throw new Exception(" Question id << "+idQuestion+" >> not found in questions in sessions");
        	    */
        	    
        	    //remove les questions dans la session 
        	    session.removeAttribute("adm_questions");
        	    
        	    request.setAttribute("questionToEdit", question_to_edit);
        	    request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
        	    
        	    
        	}catch(Exception e) {
        		e.printStackTrace();
        		// Erreur lors de la connexion à l'API, rediriger vers la page d'erreur avec l'exception
    	        request.setAttribute("javax.servlet.error.exception", e);
    	        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);

        	}
        
        //DLETE HERE
        }else if (idQuestiontoDelete != null && !idQuestiontoDelete.isEmpty()){
        	System.out.println("Delete action : " + idQuestiontoDelete);
        	try {
        	    // Convertir l'ID de la question en entier
        	    int idQuestion = Integer.parseInt(idQuestiontoDelete);
        	    
        	    ReponseCode res = service.deleteQuestion(idQuestion);

        	    // Vérifier la réponse de l'API
        	    if (res.getResponseCode() == HttpURLConnection.HTTP_OK) {
        	        request.setAttribute("classe", "success");
        	        request.setAttribute("rep_delete", res.getSuccessMessage());
        	        
        	        session.removeAttribute("adm_questions");
        	    }else {
        	        request.setAttribute("classe", "danger");
        	        request.setAttribute("rep_delete",res.getErrorMessage());
        	    }
        	    
        	    // Redirection vers une page où vous afficherez les questions (par exemple, la page Admin.jsp)
        	    request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
        	    //response.sendRedirect(request.getContextPath() + "/Admin#voir_questions");
        	
        	} catch (NumberFormatException e) {
        	    // Gérer les erreurs de conversion de l'ID en entier
        	    System.out.println("Invalid question ID format: " + idQuestiontoDelete);
        	    request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
        	} catch (Exception  e) {
        	    // Gérer les erreurs d'entrée/sortie lors de la connexion à l'API
        	    e.printStackTrace();
        	    //request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
        	 // Erreur lors de la connexion à l'API, rediriger vers la page d'erreur avec l'exception
    	        request.setAttribute("javax.servlet.error.exception", e);
    	        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);

        	}

        }else {
        	// Obtenir le chemin après "/Home"
        	String path = request.getRequestURI().substring(request.getContextPath().length() + "".length());
        	System.out.println("No action to do : path =" +path);
        	request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Update Question
		try{
			//Recuperer lq session
			HttpSession session = request.getSession(false);
			
			// Récupérer l'ID du test sélectionné dans le formulaire
            int idTest = Integer.parseInt(request.getParameter("test"));
			
			// Récupérer les données de la requête pour créer un objet Question
	        Question question = new Question(request);
	        question.setId(Integer.parseInt(request.getParameter("id")));
	        
	        //Recuperer le test:
            ArrayList<Test> tests = (ArrayList<Test>)session.getAttribute("tests");
            Test test = new Test();
            test.setId(idTest);
            for(Test t :tests) {
                if(t.getId()==idTest)
                    test=t;
            }
            question.setTest(test);
	        
	        ReponseCode res =service.updateQuestion(question);
	       
            if ( res.getResponseCode()== HttpURLConnection.HTTP_OK) {
                request.setAttribute("success",res.getSuccessMessage());

                request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
            }else {
                
                request.setAttribute("error", res.getErrorMessage());
                request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
            }
		}catch(Exception e) {
			System.out.println(" Erreur updating Question : Servlet EditDeleteQuestion doPost:\n"+e.getMessage());
			// Erreur lors de la connexion à l'API, rediriger vers la page d'erreur avec l'exception
	        request.setAttribute("javax.servlet.error.exception", e);
	        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);

		}
	}

}
