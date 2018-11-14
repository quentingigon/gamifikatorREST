package gamifikator.client;

import gamifikator.services.UserDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Stateless
public class GenericServlet extends javax.servlet.http.HttpServlet {
	final String LOGIN_JSP = "login.jsp";
	final String HOME_JSP = "home.jsp";
	final String REGISTER_JSP = "register.jsp";
	final String LOGOUT_JSP = "logout.jsp";
	final String ADMIN_JSP = "admin.jsp";

	@EJB
	UserDAOLocal userDAO;

	void checkCredentialsInSession(HttpServletRequest req,
								   HttpServletResponse resp,
								   String successPath,
								   String errorPath) throws ServletException, IOException {
		String password = (String) req.getSession().getAttribute("password");
		String email = (String) req.getSession().getAttribute("email");

		if (password != null && email != null) {
			try {
				if (userDAO.isValidUser(email, password)) {
					req.getRequestDispatcher(successPath).forward(req, resp);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			req.getRequestDispatcher(errorPath).forward(req, resp);
		}
	}

}
