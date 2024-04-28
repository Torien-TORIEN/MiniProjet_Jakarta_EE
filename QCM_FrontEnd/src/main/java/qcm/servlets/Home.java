package qcm.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet({"/Home", "/"})
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
		// Obtenir le chemin après "/Home"
			String path = request.getRequestURI().substring(request.getContextPath().length() + "/Home".length());
	    
		if(path.equalsIgnoreCase("/Test")) {
			System.out.println(" c'est test ");
			
			//Recuperer les tests 
			response.sendRedirect(request.getContextPath() + "/Test");
		}else if(path.equalsIgnoreCase("")) {
			System.out.println(" pas de path : "+ path);
			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		}else {
			System.out.println(" redirect "+ path);
			response.sendRedirect(request.getContextPath() + "/Home");
			
		}
	    // Afficher le chemin
	    System.out.println("Path après /Home : " + path);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
			
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(" Do post in HOme servlet ");
		doGet(request, response);
	}

}
