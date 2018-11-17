package gamifikator.client;

import gamifikator.model.Application;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This servlet displays the user infos and application. it also permits to upload and deploy apps
 *
 * */
@Stateless
@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends GenericServlet {

	@EJB
	private ApplicationDAOLocal appDAO;

	@EJB
	private UserDAOLocal userDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		session.setAttribute("index", 0);

		User user = null;
		try {
			// to display correct values instead of keeping user of session (in case of transaction error)
			user = userDAO.findByEmail(((User)req.getSession().getAttribute("user")).getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		user = (User)req.getSession().getAttribute("user");
		Object[] apps = appDAO.findAppsOfUserPages(user.getEmail(), 6, 0).toArray();
		req.setAttribute("applist", "_open");
		req.getSession().setAttribute("user", user);
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

		HttpSession session = req.getSession(false);

		//update Name user
		if(req.getParameter("newname") != null) {
			User currentUser = (User) req.getSession().getAttribute("user");
			currentUser.setUsername(req.getParameter("newName"));
			try {
				userDAO.update(currentUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect("home.jsp");
			return;
		}

		String appName = req.getParameter("appname");
		String appDesc = req.getParameter("appDesc");

		//update App
		if(appName != null){

			if(!appDAO.isValidAppName(appName)){
				session.setAttribute("app_error", "Application name is already used!");
				resp.sendRedirect("home.jsp");
				return;

			}

			try{
				Application app = appDAO.findAppByName(appName);
				app.setName(appName);
				if(appDesc != null && !appDesc.equals("")){
					app.setDescription(appDesc);
				}
				appDAO.update(app);


			}catch(Exception e){
				e.printStackTrace();
			}
			resp.sendRedirect("home");
			return;
		}

		String delete = req.getParameter("deleteApp");
		if(delete != null){
			try {
				Application app = appDAO.findAppByName(delete);
				appDAO.delete(app);
			}catch(Exception e){
				e.printStackTrace();
			}

			resp.sendRedirect("home");
			return;
		}


		int indexValue = (int)session.getAttribute("index");
		User user = (User)session.getAttribute("user");

		String inc = req.getParameter("increment");
		if(inc != null){
			if(inc.equals("minus") ){
				indexValue--;
			}
			if(inc.equals("plus") ){
				indexValue++;
			}

			indexValue = indexValue < 0 ? 0 : indexValue;
			session.setAttribute("index", indexValue );

			Object[] apps = appDAO.findAppsOfUserPages(user.getEmail(), 6, indexValue).toArray();

			req.setAttribute("apps", apps);
			req.getRequestDispatcher(HOME_JSP).forward(req, resp);

		}
		//TODO Error return ?




	}

}