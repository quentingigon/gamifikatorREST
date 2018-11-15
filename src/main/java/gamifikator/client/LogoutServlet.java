package gamifikator.client;

import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This servlet is logout users.
 *
 * */
@Stateless
@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends GenericServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);

		req.getSession().setAttribute("user", null);

		HttpSession session= req.getSession();
		session.invalidate();
	}
}