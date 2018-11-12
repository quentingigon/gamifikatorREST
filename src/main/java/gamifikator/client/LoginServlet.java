package gamifikator.client;


/*import gamifikator.services.MongoConnection;
import gamifikator.services.UserDAO;
import gamifikator.services.UserDAOLocal; */

import gamifikator.model.User;
import gamifikator.services.UserDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Stateless
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends javax.servlet.http.HttpServlet {

    private final String LOGIN_JSP = "login.jsp";
    private final String HOME_JSP = "home.jsp";

	@EJB
    UserDAOLocal userDAO;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

		User user = new User();

		try {
			userDAO.create(new User(
				"test",
				request.getParameter("username"),
				request.getParameter("password")
			));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			user = userDAO.findByEmail("test");
		} catch (Exception e) {
			e.printStackTrace();
		}

        if (user != null) {

			request.getSession().setAttribute("name", username);
            request.getSession().setAttribute("password", password);

            request.getRequestDispatcher(HOME_JSP).forward(request, response);
        }
        else {
            request.setAttribute("error", "Unknown user, please try again");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
