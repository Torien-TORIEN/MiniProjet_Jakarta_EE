package qcm.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qcm.models.Utilisateur;

/**
 * Servlet Filter implementation class HomeFilter
 */
@WebFilter({"/Home", "/", "/Admin","/EditDeleteQuestion","/EditDeleteTest"})
public class HomeFilter extends HttpFilter implements Filter {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public HomeFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;
	    HttpSession session = httpRequest.getSession();
	    Utilisateur user = (Utilisateur) session.getAttribute("user");

	    if (user == null) {
	        // Si l'utilisateur n'est pas connecté, redirigez-le vers la page de connexion
	        httpResponse.sendRedirect("/QCM_FrontEnd/Login");
	    } else {
	        // Vérifie si l'URL est "/Admin" et que le rôle de l'utilisateur n'est pas "ADMIN"
	        String requestURI = httpRequest.getRequestURI();
	        if ((requestURI.contains("/Admin")|| requestURI.contains("/EditDeleteQuestion") || requestURI.contains("/EditDeleteTest") )&& !user.getRole().equals("ADMIN")) {
	            // Redirige vers la page d'accueil si l'URL est "/Admin" et le rôle n'est pas "ADMIN"
	            httpResponse.sendRedirect("/QCM_FrontEnd/Home");
	        } else {
	            // Passer la requête le long de la chaîne de filtres
	            chain.doFilter(request, response);
	        }
	    }
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
