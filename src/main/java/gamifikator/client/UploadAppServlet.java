package gamifikator.client;

import gamifikator.business.PasswordUtils;
import gamifikator.model.Application;
import gamifikator.model.User;
import gamifikator.services.ApplicationDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static gamifikator.business.PasswordUtils.DEFAULT_LENGTH;

/**
 * This servlet is used to upload .war files to the server
 *
 * */
@Stateless
@WebServlet(name = "UploadAppServlet", urlPatterns = "/upload")
@MultipartConfig(location = "/",
	fileSizeThreshold = 1024 * 1024 * 10,
	maxFileSize = 1024 * 1024 * 50,
	maxRequestSize = 1024 * 1024 * 50 * 10)
public class UploadAppServlet extends GenericServlet {

	@EJB
	private ApplicationDAOLocal appDAO;

	private final String UPLOAD_DIRECTORY = "appsToDeploy";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String description = req.getParameter("appDesc");
		String appName = req.getParameter("appname");
		User owner = (User) req.getSession().getAttribute("user");

		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) uploadDir.mkdir();

		List<String> fileNames = new ArrayList<>();
		String filename;

		for (Part part : req.getParts()) {
			filename = part.getSubmittedFileName();
			if(filename != null) {
				if (!filename.endsWith(".war")) {
					req.setAttribute("upload_error", "You can only upload .war files!");
					req.getRequestDispatcher(NEWPASS_JSP).forward(req, resp);
				}
				part.write(uploadPath + File.separator + filename);
				fileNames.add(filename);
			}
		}


		for (String name: fileNames) {
			File file = new File(uploadPath + File.separator + name);
			// test if created
			if (!file.exists() || !file.isFile())
			{
				req.setAttribute("upload_error", "File was not uploaded.");
				resp.sendRedirect("/gamifikator/home");
			}
			else {
				try {

					PasswordUtils pu = new PasswordUtils(DEFAULT_LENGTH);

					//generate API key
					String apiBase = appName + owner.getEmail() + LocalDateTime.now();
					String apiKey = apiBase + pu.nextString();
					String apiSecret = apiBase + pu.nextString();

					try {
						apiKey = PasswordUtils.hash_SHA256(apiKey);
						apiSecret = PasswordUtils.hash_SHA256(apiSecret);
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
						throw new ServletException(e.getMessage());
					}

					// User owner2 = new User("macmoudi@gmail.com","macmoudi","test",false,false,true);
					appDAO.create(new Application(appName, owner, description, apiKey, apiSecret, false));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		req.setAttribute("message", "File was correctly uploaded.");
		//TODO change to forward for attribute message passing ?
		resp.sendRedirect("/gamifikator/home");
	}
}
