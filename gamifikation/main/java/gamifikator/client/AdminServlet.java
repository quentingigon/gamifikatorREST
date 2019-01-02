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
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

		req.getSession().setAttribute("message", null);

		HttpSession session = req.getSession(false);

		session.setAttribute("index", 0);
		req.setAttribute("applist", " ");


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

				session.setAttribute("index", 0);

				session.setAttribute("CURRENT_EMAIL", email);

				Object[] apps = appDAO.findAppsOfUserPages(email, 4, 0).toArray();
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
				session.setAttribute("message", "User suspended!");
				resp.sendRedirect("admin.jsp");
			}

			// admin wants to reset password of user
			if (cmd.equals("2")) {
				user.setIsPasswordValid(false);
				PasswordUtils pu = new PasswordUtils(DEFAULT_LENGTH);

				String newPass = pu.nextString();
				try {
					user.setPassword(hash_SHA256(newPass));
					userDAO.update(user);
					AdminUtils admu = new AdminUtils();
					admu.resetPassword(user.getEmail(), newPass);
				} catch (Exception e) {
					e.printStackTrace();
				}
				session.setAttribute("message", "Password reset!");
				resp.sendRedirect("admin.jsp");
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

		HttpSession session = req.getSession(false);

		int indexValue = (int)session.getAttribute("index");
		//String email = req.getParameter("current_email");
		String email = (String)session.getAttribute("CURRENT_EMAIL");

		String inc = req.getParameter("increment");
		if(inc != null){
			if(inc.equals("minus") ){
				indexValue--;
				}
			if(inc.equals("plus") ){
				indexValue++;
			}

			indexValue = indexValue < 0 ? 0 : indexValue;
			session.setAttribute("index", indexValue );


			Object[] apps = appDAO.findAppsOfUserPages(email , 4, indexValue).toArray();

			Object[] users = userDAO.getAllUsers().toArray();
			req.setAttribute("users", users);

			req.setAttribute("applist", "_open");
			req.setAttribute("apps", apps);
			req.getRequestDispatcher(ADMIN_JSP).forward(req, resp);

		}
		//TODO Error return ?
	}
}