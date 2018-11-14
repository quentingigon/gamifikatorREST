package gamifikator.client;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/gamifikator/*")
public class UserFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException { }

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

		// TODO enhance security

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(false);

		String loginURI = request.getContextPath() + "/login";
		String registerURI = request.getContextPath() + "/register";
		String elasticURI = request.getContextPath() + "/elastic";

		boolean loggedIn = session != null && session.getAttribute("user") != null;
		boolean loginOrRegisterRequest = request.getRequestURI().equals(loginURI)
										|| request.getRequestURI().equals(registerURI)
										|| request.getRequestURI().equals(elasticURI);

		if (loggedIn || loginOrRegisterRequest) {
			filterChain.doFilter(req, resp);
		}
		else {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

	@Override
	public void destroy() {

	}
}