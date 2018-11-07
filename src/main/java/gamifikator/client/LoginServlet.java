package gamifikator.client;

import com.mongodb.DBObject;
import gamifikator.mongoconnection.dao.MongoConnection;
import gamifikator.mongoconnection.dao.UserDAO;
import gamifikator.mongoconnection.models.UserDO;
import org.mongodb.morphia.query.Query;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends javax.servlet.http.HttpServlet {

	private final String LOGIN_JSP = "login.jsp";
	private final String HOME_JSP = "home.jsp";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);

		UserDO user = new UserDO(
			req.getParameter("email"),
			req.getParameter("password")
		);

		MongoConnection conn = MongoConnection.getInstance();
		UserDAO userDao = new UserDAO(conn.getDatastore());

		Query query = conn.getDatastore().createQuery(UserDO.class)
			.field("email").equal(user.getEmail())
			.field("password").equal(user.getPassword());

		if (query.asList().size() == 1) {
			DBObject tmp = conn.getMorphia().toDBObject(user);

			// set token here ? httpsession ? cookies ?

			this.getServletContext().getRequestDispatcher(HOME_JSP).forward(req, resp);
		}
		else {
			this.getServletContext().getRequestDispatcher(LOGIN_JSP).forward(req, resp);
		}
	}
}
