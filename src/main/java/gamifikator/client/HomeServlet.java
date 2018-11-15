package gamifikator.client;

import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

	}
}