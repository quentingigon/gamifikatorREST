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
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * This servlet is used when a user needs to reset his password.
 *
 * */
@Stateless
@WebServlet(name = "NewPasswordServlet", urlPatterns = "/newpass")
public class NewPasswordServlet extends GenericServlet {

	@EJB
	private UserDAOLocal userDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("newpass_error", "You must choose a new password.");
		req.getRequestDispatcher(NEWPASS_JSP).forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String newPassword = req.getParameter("new_password");

		User user = new User();

		try {
			user = userDAO.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpSession session = req.getSession(false);


		if (user == null) {
			session.setAttribute("newpass_error", "User doesn't exists.");
			resp.sendRedirect("newpass.jsp");
		}
		else {
			try {
				// set new password
				user.setIsPasswordValid(true);
				user.setPassword(PasswordUtils.hash_SHA256(newPassword));
				userDAO.update(user);
				// AdminUtils admu = new AdminUtils();
				// admu.resetPassword(user.getEmail(), PasswordUtils.hash_SHA256(newPassword));
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("login_error", null);
			resp.sendRedirect("login.jsp");
		}
	}
}
