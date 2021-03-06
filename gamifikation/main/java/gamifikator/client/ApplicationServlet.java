package gamifikator.client;

import gamifikator.business.PasswordUtils;
import gamifikator.model.Application;
import gamifikator.model.User;
import gamifikator.services.ApplicationDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static gamifikator.business.PasswordUtils.DEFAULT_LENGTH;

/**
 * This servlet is used to upload .war files to the server
 *
 * */
@Stateless
@WebServlet(name = "ApplicationServlet", urlPatterns = "/app")
public class ApplicationServlet extends GenericServlet {

	@EJB
	private ApplicationDAOLocal appDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("home");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		// get file and user info
		String description = req.getParameter("appDesc");

		String appName = req.getParameter("appname");
		User owner = (User) req.getSession().getAttribute("user");


		//check if description isn't too long
		if(description.length() > 400){
			session.setAttribute("app_error", "Application description too long!");
			resp.sendRedirect("home");
			return;

		}

		// check if name is already used by an app in db
		if (!appDAO.isValidAppName(appName)) {
			Date createDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String currentTime = sdf.format(createDate);

			PasswordUtils pu = new PasswordUtils(DEFAULT_LENGTH);

			// generate API key
			String apiBase = appName + owner.getEmail() + LocalDateTime.now();
			String apiKey = apiBase + pu.nextString();
			String apiSecret = apiBase + pu.nextString();

			// add app to database
			try {
				apiSecret = PasswordUtils.hash_SHA256(apiSecret);
				apiKey = PasswordUtils.hash_SHA256(apiKey);
				appDAO.create(new Application(appName, owner.getEmail(), currentTime, description, apiKey, apiSecret, false));
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			session.setAttribute("app_error", "");

		}
		else {
			session.setAttribute("app_error", "Application name is already used!");
		}
		resp.sendRedirect("home");
	}
}
