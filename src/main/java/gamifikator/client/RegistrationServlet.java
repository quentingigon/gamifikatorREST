package gamifikator.client;

import com.mongodb.DBObject;
import gamifikator.model.User;
import gamifikator.services.MongoConnection;
import gamifikator.services.UserDAO;
import gamifikator.services.UserDAOLocal;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/register")
public class RegistrationServlet extends javax.servlet.http.HttpServlet {

	private final String REGISTER_JSP = "register.jsp";
	private final String LOGIN_JSP = "login.jsp";

	@EJB
	UserDAOLocal userDAO;

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

		String passwordConf = req.getParameter("passwordConf");

		User user = new User(
			req.getParameter("firstName"),
			req.getParameter("lastName"),
			req.getParameter("email"),
			req.getParameter("password")
		);

		if (user.getPassword().equals(passwordConf)) {
			MongoConnection conn = MongoConnection.getInstance();
			userDAO = new UserDAO(conn.getDatastore());

			DBObject tmp = conn.getMorphia().toDBObject(user);

			((UserDAO) userDAO).getCollection().insert(tmp);

			resp.sendRedirect(req.getContextPath() + LOGIN_JSP);
		}
		else {
			this.getServletContext().getRequestDispatcher(REGISTER_JSP).forward(req, resp);
		}


	}
}
