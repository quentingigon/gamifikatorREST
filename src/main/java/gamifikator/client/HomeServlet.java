package gamifikator.client;

import static gamifikator.business.PasswordUtils.DEFAULT_LENGTH;

import gamifikator.business.PasswordUtils;
import gamifikator.model.Application;
import gamifikator.model.User;
import gamifikator.services.ApplicationDAO;

import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Date;


@Stateless
@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends GenericServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO setAttribute with list of application for the connected dev
        String[][] list = new String [][]
                {{"app1", "allow fun", "12-08-18", "G74F1AD24AF"},
                 {"app2", "allow nofun", "12-08-18", "G74F1AD24AF"}};

        req.setAttribute("listApp",list); //Setting UsernameLabel to mes_add_pageTitle

        req.getRequestDispatcher(HOME_JSP).forward(req,resp);


    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    //setting it up
        ApplicationDAO AD = new ApplicationDAO();
        Application app = new Application();
        PasswordUtils pu = new PasswordUtils(DEFAULT_LENGTH);

        User user = (User)req.getSession(false).getAttribute("user");

        app.setName( req.getParameter("appname") );
        app.setDescription( req.getParameter("appDesc") );
        app.setOwner( user );
        app.setDeployed( false );


        //generate API key
        String apiBase = app.getName() + app.getOwner() + LocalDateTime.now();
        String apiKey = apiBase + pu.nextString();
        String apiSecret = apiBase + pu.nextString();

        try {
            apiKey = PasswordUtils.hash_SHA256(apiKey);
            apiSecret = PasswordUtils.hash_SHA256(apiSecret);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }

        app.setApiKey(apiKey);
        app.setApiSecret(apiSecret);

        AD.create(app);



    }
}