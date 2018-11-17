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
import java.security.NoSuchAlgorithmException;


/**
 * This servlet is used to handle all the login logic,
 * with errors and state of user account handling
 *
 * */
@Stateless
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends GenericServlet {

	@EJB
	private UserDAOLocal userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
		String password = "";

		try {
			password = PasswordUtils.hash_SHA256(req.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		User user = null;

		try {
			user = userDAO.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user == null) {
			req.setAttribute("login_error", "Bad user");
			req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
		}
		else {

			HttpSession session = req.getSession(false);

			// accout suspended
			if (user.isSuspended()) {
				session.setAttribute("login_error", "You have been suspended! Do not come and tell us it was a mistake, you surely deserved it.");
				resp.sendRedirect("login.jsp");
			}

			// password must be changed
			else if (!user.isPasswordValid()) {
				session.setAttribute("login_error", "You have to chose a new password.");
				resp.sendRedirect("newpass.jsp");
			}

			// logged in
			else if (user.getPassword().equals(password)) {
				session.setAttribute("user", user);
				req.setAttribute("login_error", null);
				resp.sendRedirect("home");
			}

			else {
				req.setAttribute("login_error", "Bad password");
				req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
			}
		}
    }
}
