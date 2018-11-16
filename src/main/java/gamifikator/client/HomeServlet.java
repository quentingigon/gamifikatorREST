package gamifikator.client;

import gamifikator.model.User;
import gamifikator.services.ApplicationDAOLocal;
import gamifikator.services.AppsDeployer;
import gamifikator.services.UserDAO;
import gamifikator.services.UserDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * This servlet displays the user infos and application. it also permits to upload and deploy apps
 *
 * */
@Stateless
@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends GenericServlet {

	@EJB
	ApplicationDAOLocal appDAO;

	@EJB
	UserDAOLocal userDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO setAttribute with list of application for the connected dev
		String deploy = req.getParameter("deploy");
		String undeploy = req.getParameter("undeploy");
		String selectedApp = req.getParameter("appToDeploy");

        User user = (User)req.getSession().getAttribute("user");
		// Object[] apps = appDAO.getAllApplicationsOfUserByCreator(user.getUsername()).toArray();
		req.setAttribute("applist", "_open");
	//	req.setAttribute("apps", apps);

		if (deploy != null) {
			AppsDeployer deployer = new AppsDeployer();
			if (selectedApp != null) {
				deployer.deployUserApp(getServletContext().getRealPath("") + "/appsToDeploy/"
					+ user.getUsername() + "/" + selectedApp);
			}
			else {
				deployer.deployAllUserApps(getServletContext().getRealPath("") + "/appsToDeploy" + "/" + user.getUsername());
			}
		}
		else if (undeploy != null) {
			AppsDeployer deployer = new AppsDeployer();
			if (selectedApp != null) {
				deployer.undeployUserApp(getServletContext().getRealPath("") + "/appsToDeploy/"
					+ user.getUsername() + "/" + selectedApp);
			}
			else {
				deployer.undeployAllUserApps( getServletContext().getRealPath("") + "/" + user.getUsername());
			}
		}

		req.getRequestDispatcher(HOME_JSP).forward(req,resp);
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User currentUser = (User) req.getSession().getAttribute("user");
		currentUser.setUsername(req.getParameter("newName"));
		try {
			userDAO.update(currentUser);

		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher(HOME_JSP).forward(req,resp);

	}
}