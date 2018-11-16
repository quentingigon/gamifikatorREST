package gamifikator.client;

import gamifikator.model.User;
import gamifikator.services.ApplicationDAOLocal;

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
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO setAttribute with list of application for the connected dev

        User user = (User)req.getSession().getAttribute("user");
	//	Object[] apps = appDAO.getAllApplicationsOfUserByEmail(user.getEmail()).toArray();
		req.setAttribute("applist", "_open");
	//	req.setAttribute("apps", apps);


		req.getRequestDispatcher(HOME_JSP).forward(req,resp);


    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}