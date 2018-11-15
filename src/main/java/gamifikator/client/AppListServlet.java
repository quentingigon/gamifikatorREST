package gamifikator.client;

import gamifikator.services.ApplicationDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Stateless
@WebServlet(name = "AppListServlet", urlPatterns = "/applist")
public class AppListServlet extends GenericServlet {

	@EJB
	ApplicationDAOLocal appDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");

		Object[] apps = appDAO.getAllApplicationsOfUserByEmail(email).toArray();

		req.setAttribute("apps", apps);
		req.getRequestDispatcher(ADMIN_JSP).forward(req, resp);
	}
}
