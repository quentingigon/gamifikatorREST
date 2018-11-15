package gamifikator.client;

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

		Object[] users = userDAO.getAllUsers().toArray();

		Object[] apps = appDAO.getAllApplicationsOfUserByEmail(email).toArray();

		req.setAttribute("apps", apps);
		req.setAttribute("users", users); 
		req.getRequestDispatcher(ADMIN_JSP).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}