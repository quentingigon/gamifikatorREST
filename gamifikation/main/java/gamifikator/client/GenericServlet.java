package gamifikator.client;

import javax.ejb.Stateless;

/**
 * This is a generic servlet
 *
 * */
@Stateless
public class GenericServlet extends javax.servlet.http.HttpServlet {

	final String LOGIN_JSP = "login.jsp";
	final String HOME_JSP = "home.jsp";
	final String REGISTER_JSP = "register.jsp";
	final String NEWPASS_JSP = "newpass.jsp";
	final String ADMIN_JSP = "admin.jsp";
}
