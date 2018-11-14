package gamifikator.client;

import gamifikator.model.User;

import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Stateless
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends GenericServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		checkCredentialsInSession(req, resp, HOME_JSP, LOGIN_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

		User user = new User();

		try {
			user = userDAO.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

        if (user.getEmail() != null) {
			HttpSession session = req.getSession(true); // revalidate the session if invalidated
			session.setAttribute("email", email);
			session.setAttribute("password", password);

            req.getRequestDispatcher(HOME_JSP).forward(req, resp);
        }
        else {
            req.setAttribute("error", "Unknown user, please try again");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
