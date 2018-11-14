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
import javax.servlet.http.HttpSession;
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
		checkCredentialsInSession(req, resp, HOME_JSP, REGISTER_JSP);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String passwordConf = req.getParameter("passwordConf");
		String password = req.getParameter("password");
		String username = req.getParameter("username");
		String email = req.getParameter("email");

		if (password.equals(passwordConf)) {
			try {
				userDAO.create(new User(
					username,
					email,
					password
				));
			} catch (Exception e) {
				e.printStackTrace();
			}

			HttpSession session = req.getSession(true); // revalidate the session if invalidated
			session.setAttribute("username", username);
			session.setAttribute("password", password);

			req.getRequestDispatcher(HOME_JSP).forward(req, resp);
		}
		else {
			this.getServletContext().getRequestDispatcher(REGISTER_JSP).forward(req, resp);
		}


	}
}
