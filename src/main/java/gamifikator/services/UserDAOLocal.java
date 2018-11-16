package gamifikator.services;

import gamifikator.model.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Local interface for our user DAO
 *
 * */
@Local
public interface UserDAOLocal extends IGenericDAO<User>{

	/**
	 * Find a user with his email
	 *
	 * @param email Email of user to find (PK)
	 *
	 * @return User if found, null otherwise
	 *
	 * */
	User findByEmail(String email) throws Exception;

	/**
	 * Verify if a user exists
	 *
	 * @param email Email of user to validate
	 *
	 * @return true if valid, false otherwise
	 *
	 * */
	boolean isValidUser(String email, String username) throws Exception;

	/**
	 * Get all users
	 *
	 * @return All users in a list
	 *
	 * */
	List getAllUsers();

	List getUsersPages(int pageSize, int pageIndex);

}
