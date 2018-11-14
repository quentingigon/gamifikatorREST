package gamifikator.business;

import gamifikator.model.User;
import gamifikator.services.UserDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AdminUtils {

	@EJB
	UserDAOLocal userDAO;

	public void makeUserAdmin(String adminEmail, String userEmail) throws Exception {
		User admin = userDAO.findByEmail(adminEmail);
		User user = userDAO.findByEmail(userEmail);

		if (user == null || !admin.isAdmin()) {
			throw new Exception("You have to be an admin to make people admin, you cheaty guy.");
		}

		user.setAdmin(true);
		userDAO.update(user);
	}

	public void resetPassword(String adminEmail, String userEmail, String password) throws Exception {
		User admin = userDAO.findByEmail(adminEmail);
		User user = userDAO.findByEmail(userEmail);

		if (user == null || !admin.isAdmin()) {
			throw new Exception("You have to be an admin to reset passwords, you funny guy.");
		}

		user.setPassword(password);
		userDAO.update(user);
		EmailUtils emailUtils = new EmailUtils();
		emailUtils.sendPasswordByEmail(user, password);
	}

	public void suspendAccount(String adminEmail, String userEmail) throws Exception {
		User admin = userDAO.findByEmail(adminEmail);
		User user = userDAO.findByEmail(userEmail);

		if (user == null || !admin.isAdmin()) {
			throw new Exception("You have to be an admin to suspend accounts, you smelly guy.");
		}

		user.setSuspended(true);
		userDAO.update(user);
	}
}
