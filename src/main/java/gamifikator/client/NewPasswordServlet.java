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
@WebServlet(name = "NewPasswordServlet", urlPatterns = "/newpass")
public class NewPasswordServlet extends GenericServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(NEWPASS_JSP).forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String newPassword = req.getParameter("new_password");

		User user = new User();

		try {
			user = userDAO.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user == null) {
			req.setAttribute("newpass_error", "User doesn't exists.");
			req.getRequestDispatcher(NEWPASS_JSP).forward(req, resp);
		}
		else {
			user.setPassword(newPassword);
			try {
				userDAO.update(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect("/gamifikator/home");
		}
	}
}
