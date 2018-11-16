package gamifikator.client;

import gamifikator.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This filter is used to control the access to admin section
 *
 * */
@WebFilter("/*")
public class AdminFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) { }

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(false);

		String adminURI = request.getContextPath() + "/admin";

		// check if current user is logged as admin
		boolean isAdmin = session != null && session.getAttribute("user") != null
							&& ((User) session.getAttribute("user")).isAdmin();

		// check if route is part of restricted routes
		boolean isRouteRestricted = request.getRequestURI().equals(adminURI);

		if (isAdmin || !isRouteRestricted) {
			filterChain.doFilter(req, resp);
		}
		else {
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		}
	}

	@Override
	public void destroy() {

	}
}
