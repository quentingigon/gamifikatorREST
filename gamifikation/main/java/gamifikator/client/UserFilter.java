package gamifikator.client;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This filter is used to verify that the visitor is logged
 *
 * */
@WebFilter("/*")
public class UserFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(false);

		String loginURI = request.getContextPath() + "/login";
		String registerURI = request.getContextPath() + "/register";
		String elasticURI = request.getContextPath() + "/elastic";
		String logosURI = request.getContextPath() + "/logos";
		String newpassURI = request.getContextPath() + "/newpass";

		// true if user is logged in
		boolean loggedIn = session != null && session.getAttribute("user") != null;
		// true if request come from authorized routes
		boolean loginOrRegisterRequest = request.getRequestURI().equals(loginURI)
										|| request.getRequestURI().equals(registerURI)
										|| request.getRequestURI().contains(elasticURI)
										|| request.getRequestURI().contains(logosURI)
										|| request.getRequestURI().contains(newpassURI);

		if (loggedIn || loginOrRegisterRequest) {
			filterChain.doFilter(req, resp);
		}
		else {
			response.sendRedirect("login");
		}
	}

	@Override
	public void destroy() {

	}
}
