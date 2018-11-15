package gamifikator.client;

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
			if (password.equals(passwordConf) && !userDAO.isValidUser(email)) {

				User user = new User(
					email,
					username,
					password,
					false,
					false,
					true
				);
				userDAO.create(user);

				req.getSession().setAttribute("user", user);
				req.setAttribute("register_error", null);
				resp.sendRedirect("/gamifikator/home");
			}
			else {
				req.setAttribute("register_error", "Email already used.");
				resp.sendRedirect("/gamifikator/register");
				// this.getServletContext().getRequestDispatcher(REGISTER_JSP).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
