package qcm.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import qcm.models.Question;
import qcm.models.Test;
import qcm.services.ReponseCode;
import qcm.services.TestService;

/**
 * Servlet implementation class EditDeleteTest
 */
@WebServlet("/EditDeleteTest")
public class EditDeleteTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TestService service;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteTest() {
        super();
        // TODO Auto-generated constructor stub
        service=new TestService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérer l'ID du test sélectionné dans le formulaire
        String idTestEdit = request.getParameter("id_testEdit");
        String idTestDelete = request.getParameter("id_testDelete");
        HttpSession session = request.getSession(false);
        
        // EDIT HERE
        if (idTestEdit != null && !idTestEdit.isEmpty()) {
        	System.out.println("Edit Test ");
        	try {
        		int idTest = Integer.parseInt(idTestEdit);
        		
        		
        		Test test = null;
        		ArrayList<Test> tests = (ArrayList<Test> ) session.getAttribute("tests");
        		if(tests !=null && !tests.isEmpty()) {
        			for(Test t :tests) {
        				if(t.getId()==idTest) {
        					test=t;
        				}
        			}
        		}
        		
        		if(test !=null) {
        			request.setAttribute("test_to_edit", test);
        		}
        		
        		request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
        		
        	}catch(Exception e ) {
        		e.printStackTrace();
        		// Erreur lors de la connexion à l'API, rediriger vers la page d'erreur avec l'exception
    	        request.setAttribute("javax.servlet.error.exception", e);
    	        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);

        	}
        	
        	
        	
        //DELETE HERE
        }else if(idTestDelete != null && !idTestDelete.isEmpty()) {
        	System.out.println("Delete Test ");
        	try {
        		int idTest = Integer.parseInt(idTestDelete);
        		
        		ReponseCode res= service.deleteTest(idTest);
        		int responseCode = res.getResponseCode();
        		
    	        if (responseCode == HttpURLConnection.HTTP_OK) {
    	            
    	            request.setAttribute("test_success",res.getSuccessMessage() );
    	            
    	            //Resets sessions
    	            ArrayList<Test> tests= service.getAllTests();
    	         // Mettre les tests dans la session
    	            if(tests!=null && !tests.isEmpty())
    	            	request.getSession().setAttribute("tests", tests);
    	            
    	         // Redirige vers la page de connexion
    		        request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
    	        }else{
                    request.setAttribute("test_error", res.getErrorMessage());
                    // Rediriger vers la page JSP appropriée
                    request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
                }
        	}catch(Exception e) {
        		System.out.println("Erreur "+e.getMessage());
        		e.printStackTrace();
        		// Erreur lors de la connexion à l'API, rediriger vers la page d'erreur avec l'exception
    	        request.setAttribute("javax.servlet.error.exception", e);
    	        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);

        	}
        	
        	
        	
        	
        }else {
        	System.out.println("No Action Test ");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idTestToEdit= (String) request.getParameter("id_test_to_edit");
			
			//UPDATE ICI
			if(idTestToEdit!=null) {
				System.out.println("UPDATING TEST");
				Test test= new Test(request);
				test.setId(Integer.parseInt(idTestToEdit));
				System.out.println("Test :\n "+ test);
				
				// Appelle l'API pour ajouter l'utilisateur
		        ReponseCode res = service.updateTest(test);
		        
		        if (res.getResponseCode() == HttpURLConnection.HTTP_OK) {
		            
		            request.setAttribute("test_success",res.getSuccessMessage() );
		            
		            //Resets sessions
		            ArrayList<Test> tests= service.getAllTests();
		         // Mettre les tests dans la session
		            if(tests!=null && !tests.isEmpty())
		            	request.getSession().setAttribute("tests", tests);
		            
		         // Redirige vers la page de connexion
			        request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
		        }else{
	                // Ajoutez le message d'erreur à l'attribut "error" pour le transmettre à la page JSP
	                request.setAttribute("test_error", res.getErrorMessage());
	                // Rediriger vers la page JSP appropriée
	                request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
	            	
	            }
				
				
				
			// AJOUT ICI	
			}else {
				System.out.println("ADDING  TEST");
				// Récupérer les données de la requête pour créer un objet Question
	            Test test = new Test(request);
	            
	         // Appelle l'API pour ajouter l'utilisateur
		        ReponseCode res = service.addTest(test);
	
		        int responseCode = res.getResponseCode();
		        if (responseCode == HttpURLConnection.HTTP_CREATED) {
		            
		            request.setAttribute("test_success",res.getSuccessMessage() );
		            
		            //Resets sessions
		            ArrayList<Test> tests= service.getAllTests();
		         // Mettre les tests dans la session
		            if(tests!=null && !tests.isEmpty())
		            	request.getSession().setAttribute("tests", tests);
		            
		         // Redirige vers la page de connexion
			        request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
		        }else{
	                // Ajoutez le message d'erreur à l'attribut "error" pour le transmettre à la page JSP
	                request.setAttribute("test_error", res.getErrorMessage());
	                // Rediriger vers la page JSP appropriée
	                request.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
	            	
	            }
			}
            
		}catch(Exception e) {
			e.printStackTrace();		}
	}

}
