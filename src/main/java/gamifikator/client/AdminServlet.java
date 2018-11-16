package gamifikator.client;

import gamifikator.business.AdminUtils;
import gamifikator.business.PasswordUtils;
import gamifikator.model.User;
import gamifikator.services.ApplicationDAOLocal;
import gamifikator.services.UserDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static gamifikator.business.PasswordUtils.DEFAULT_LENGTH;

/**
 * This servlet is used by the admin section to receive / send data
 * and process actions on users by the admin
 *
 * */
@Stateless
@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends GenericServlet {

	@EJB
	private UserDAOLocal userDAO;

	@EJB
	private ApplicationDAOLocal appDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String cmd = req.getParameter("cmd");

		// no button pressed
		if (cmd == null || cmd.equals("0")) {
			if (email == null) {
				Object[] users = userDAO.getAllUsers().toArray();
				req.setAttribute("users", users);
			}
			else {
				User user = null;
				try {
					user = userDAO.findByEmail(email);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Object[] apps = appDAO.getAllApplicationsOfUserByCreator(user.getUsername()).toArray();
				// req.setAttribute("applist", "_open");
				req.setAttribute("apps", apps);
			}
			req.getRequestDispatcher(ADMIN_JSP).forward(req, resp);
		}

		User user = null;
		if (email != null) {
			try {
				user = userDAO.findByEmail(email);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (user != null)  {
			// admin wants to suspend user
			if (cmd.equals("1")) {
				// AdminUtils admu = new AdminUtils();
				try {
					user.setSuspended();
					userDAO.update(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
				req.getSession().setAttribute("message", "User suspended!");
				//resp.sendRedirect("/gamifikator/admin");
				req.getRequestDispatcher(ADMIN_JSP).forward(req, resp);
			}

			// admin wants to reset password of user
			if (cmd.equals("2")) {
				user.setIsPasswordValid(false);
				PasswordUtils pu = new PasswordUtils(DEFAULT_LENGTH);
				AdminUtils admu = new AdminUtils();

				String newPass = pu.nextString();
				try {
					admu.resetPassword(user.getEmail(), newPass);
				} catch (Exception e) {
					e.printStackTrace();
				}
				req.getSession().setAttribute("message", "Password reset!");
				req.getRequestDispatcher(ADMIN_JSP).forward(req, resp);
			}
			try {
				userDAO.update(user);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}