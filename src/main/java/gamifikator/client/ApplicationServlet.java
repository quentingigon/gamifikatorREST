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
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// get file and user info
		String description = req.getParameter("appDesc");
		String createDate = req.getParameter("create_date");
		String appName = req.getParameter("appname");
		User owner = (User) req.getSession().getAttribute("user");

		// check if name is already used by an app in db
		if (appDAO.isValidAppName(appName)) {
			String test= "29-Apr-2010";
			DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy");
			Date testDate = null;
			try {
				testDate = formatter.parse(test);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			PasswordUtils pu = new PasswordUtils(DEFAULT_LENGTH);

			// generate API key
			String apiBase = appName + owner.getEmail() + LocalDateTime.now();
			String apiKey = apiBase + pu.nextString();
			String apiSecret = apiBase + pu.nextString();

			// add app to database
			try {
				apiSecret = PasswordUtils.hash_SHA256(apiSecret);
				apiKey = PasswordUtils.hash_SHA256(apiKey);
				appDAO.create(new Application(appName, owner.getEmail(), testDate, description, apiKey, apiSecret, false));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else {
			req.setAttribute("app_error", "Application name is already used!");
		}
		req.getRequestDispatcher(HOME_JSP).forward(req,resp);
	}
}
