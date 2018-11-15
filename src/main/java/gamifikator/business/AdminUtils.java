package gamifikator.business;

import gamifikator.model.User;
import gamifikator.services.UserDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Methods used by the admin
 * 
 * */
@Stateless
public class AdminUtils {

	@EJB
	private UserDAOLocal userDAO;

	/**
	 * Make a user admin
	 *
	 * @param userEmail user to make admin
	 *
	 * */
	public void makeUserAdmin(String userEmail) throws Exception {
		User user = userDAO.findByEmail(userEmail);

		if (user == null) {
			throw new Exception("You have to be an admin to make people admin, you cheaty guy.");
		}

		user.setAdmin(true);
		userDAO.update(user);
	}

	/**
	 * Reset password of certain user
	 *
	 * @param userEmail user with reseted password
	 * @param password new password
	 *
	 * */
	public void resetPassword(String userEmail, String password) throws Exception {
		User user = userDAO.findByEmail(userEmail);

		if (user == null) {
			throw new Exception("You have to be an admin to reset passwords, you funny guy.");
		}

		user.setPassword(password);
		userDAO.update(user);
		EmailUtils emailUtils = new EmailUtils();
		emailUtils.sendPasswordByEmail(user, password);
	}

	/**
	 * Suspend account of certain user
	 *
	 * @param userEmail user with account suspended
	 *
	 * */
	public void suspendAccount(String userEmail) throws Exception {
		User user = userDAO.findByEmail(userEmail);

		if (user == null) {
			throw new Exception("You have to be an admin to suspend accounts, you smelly guy.");
		}

		user.setSuspended(true);
		userDAO.update(user);
	}
}
