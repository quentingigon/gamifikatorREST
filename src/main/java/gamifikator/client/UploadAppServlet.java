package gamifikator.client;

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
import java.util.ArrayList;
import java.util.List;


@Stateless
@WebServlet(name = "UploadAppServlet", urlPatterns = "/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
	maxFileSize = 1024 * 1024 * 5,
	maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadAppServlet extends GenericServlet {

	@EJB
	ApplicationDAOLocal appDAO;

	private final String UPLOAD_DIRECTORY = "appsToDeploy";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String description = req.getParameter("appDesc");
		String appName = req.getParameter("appname");
		User owner = (User) req.getAttribute("user");

		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) uploadDir.mkdir();

		List<String> fileNames = new ArrayList<>();
		String fileName;

		for (Part part : req.getParts()) {
			fileName = part.getSubmittedFileName();
			if(fileName != null) {
				part.write(uploadPath + File.separator + fileName);
				fileNames.add(fileName);
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
					User owner2 = new User("macmoudi@gmail.com","macmoudi","test",false,false,true);
					appDAO.create(new Application(appName, owner2, description, false));
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
