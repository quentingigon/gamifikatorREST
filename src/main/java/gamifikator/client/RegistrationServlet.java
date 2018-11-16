package gamifikator.client;

import gamifikator.business.PasswordUtils;
import gamifikator.model.User;
import gamifikator.services.UserDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet is used to register new users.
 *
 * */
@Stateless
@WebServlet(name = "RegistrationServlet", urlPatterns = "/register")
public class RegistrationServlet extends GenericServlet {

	@EJB
	private UserDAOLocal userDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(REGISTER_JSP).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String passwordConf = req.getParameter("confirm_password");
		String password = req.getParameter("password");
		String username = req.getParameter("username");
		String email = req.getParameter("email");

		try {
			// if passwords are equals and email is not in database
			if (password.equals(passwordConf) && !userDAO.isValidUser(email, username)) {

				User user = new User(
					email,
					username,
					PasswordUtils.hash_SHA256(password),
					true,
					false,
					true
				);
				userDAO.create(user);

				req.getSession().setAttribute("user", user);
				req.setAttribute("register_error", null);
				req.getRequestDispatcher(HOME_JSP).forward(req, resp);
			}
			else {
				req.setAttribute("register_error", "Email or username already used.");
				req.getRequestDispatcher(REGISTER_JSP).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
