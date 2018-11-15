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
import java.io.IOException;

@Stateless
@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends GenericServlet {

	@EJB
	UserDAOLocal userDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		User admin = (User) req.getSession().getAttribute("user");

		Object[] users = userDAO.getAllUsers().toArray();

		// ArrayList<Application> applications = appDAO.getAllApplicationsOfUserByEmail();

		req.setAttribute("users", users); //Setting UsernameLabel to mes_add_pageTitle
		req.getRequestDispatcher(ADMIN_JSP).forward(req, resp);  //forwarded to welcome.jsp
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}