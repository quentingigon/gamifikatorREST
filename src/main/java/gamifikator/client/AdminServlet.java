package gamifikator.client;

import gamifikator.business.AdminUtils;
import gamifikator.business.EmailUtils;
import gamifikator.business.PasswordUtils;
import gamifikator.model.Application;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import static gamifikator.business.PasswordUtils.DEFAULT_LENGTH;
import static gamifikator.business.PasswordUtils.hash_SHA256;

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

			Object[] users = userDAO.getAllUsers().toArray();
			req.setAttribute("users", users);

			if (email != null) {

				User user = null;

				try {
					user = userDAO.findByEmail(email);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Application app = new Application("name", user.getEmail(), true);
				app.setDescription("test description");
				Date createDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				app.setCreateDate(sdf.format(createDate));

				try {
					appDAO.create(app);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Object[] apps = appDAO.getAllApplicationsOfUserByEmail(user.getEmail()).toArray();
				req.setAttribute("applist", "_open");
				req.setAttribute("apps", apps);
			}
			req.getSession().setAttribute("message", null);
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

		if (user != null && cmd != null)  {
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
					user.setPassword(hash_SHA256(newPass));
					userDAO.update(user);
					EmailUtils emu = new EmailUtils();
					emu.sendPasswordByEmail(user.getEmail(), newPass);
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