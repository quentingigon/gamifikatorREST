package gamifikator.client;

import com.mongodb.DBObject;
import gamifikator.mongoconnection.dao.MongoConnection;
import gamifikator.mongoconnection.dao.UserDAO;
import gamifikator.mongoconnection.models.UserDO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends javax.servlet.http.HttpServlet {

	private final String REGISTER_JSP = "register.jsp";
	private final String LOGIN_JSP = "login.jsp";

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

		UserDO user = new UserDO(
			req.getParameter("firstName"),
			req.getParameter("lastName"),
			req.getParameter("email"),
			req.getParameter("password")
		);

		if (user.getPassword().equals(passwordConf)) {
			MongoConnection conn = MongoConnection.getInstance();
			UserDAO userDao = new UserDAO(conn.getDatastore());

			DBObject tmp = conn.getMorphia().toDBObject(user);

			userDao.getCollection().insert(tmp);

			resp.sendRedirect(req.getContextPath() + LOGIN_JSP);
		}
		else {
			this.getServletContext().getRequestDispatcher(REGISTER_JSP).forward(req, resp);
		}


	}
}
