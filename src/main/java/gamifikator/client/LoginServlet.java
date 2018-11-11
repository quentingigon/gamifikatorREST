package gamifikator.client;

import gamifikator.model.User;
import gamifikator.services.UserDAO;
import gamifikator.services.UserDAOLocal;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends javax.servlet.http.HttpServlet {

	private final String LOGIN_JSP = "login.jsp";
	private final String HOME_JSP = "home.jsp";

	@EJB
	UserDAOLocal userDAO;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);

		User user = new User(
			req.getParameter("lastName"),
			req.getParameter("firstName"),
			req.getParameter("email"),
			req.getParameter("password")
		);

		userDAO = new UserDAO();

		if (userDAO.findByEmail(user.getEmail()).getPassword().equals(user.getPassword())) {

			// set token here ? httpsession ? cookies ?

			this.getServletContext().getRequestDispatcher(HOME_JSP).forward(req, resp);
		}
		else {
			this.getServletContext().getRequestDispatcher(LOGIN_JSP).forward(req, resp);
		}
	}
}
