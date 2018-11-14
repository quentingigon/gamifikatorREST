package gamifikator.client;

import gamifikator.model.User;

import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);    }

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

		if (user.isSuspended()) {
			req.setAttribute("error", "You have been suspended! Do not come and tell us it was a mistake, you surely deserved it.");
			req.getRequestDispatcher(ADMIN_JSP).forward(req, resp);
		}

        if (user.getEmail() != null) {
			req.getSession().setAttribute("user", user);

            // req.getRequestDispatcher(HOME_JSP).forward(req, resp);
			resp.sendRedirect("/gamifikator/home");
        }
        else {
            req.setAttribute("error", "Unknown user, please try again");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
