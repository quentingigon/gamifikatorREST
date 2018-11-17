package gamifikator.client;

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
import java.io.IOException;

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

        User user = (User)req.getSession().getAttribute("user");
		Object[] apps = appDAO.getAllApplicationsOfUserByEmail(user.getEmail()).toArray();
		req.setAttribute("applist", "_open");
		req.setAttribute("apps", apps);
		req.getRequestDispatcher(HOME_JSP).forward(req,resp);

		// .war deployment

		/*
		String deploy = req.getParameter("deploy");
		String undeploy = req.getParameter("undeploy");
		String selectedApp = req.getParameter("appToDeploy");
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
		}*/
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