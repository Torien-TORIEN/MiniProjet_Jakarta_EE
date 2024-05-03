package qcm.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PaginnationServlet
 */
@WebServlet("/Paginnation")
public class PaginnationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginnationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Ceci est est resevé à la page Admin (Admin.jsp)
		
		try {
			String page = request.getParameter("page");
			HttpSession session = request.getSession(false);
			
			//Afficher le test
			if(page!=null && !page.isEmpty() && page.equals("test")) {
				session.setAttribute("page", page);
				System.out.println("Test section");
			}
			//Afficher le list des questions.
			else if (page!=null && !page.isEmpty() && page.equals("add_question")) {
				session.setAttribute("page", page);
				System.out.println("add_question section");
			}
			// Afficher le add ou edit Question
			else {
				session.removeAttribute("page");
				System.out.println("Pagination");
			}
			response.sendRedirect(request.getContextPath() + "/Admin");
		}catch(Exception e) {
			e.printStackTrace();
			// Erreur lors de la connexion à l'API, rediriger vers la page d'erreur avec l'exception
	        request.setAttribute("javax.servlet.error.exception", e);
	        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);

		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
