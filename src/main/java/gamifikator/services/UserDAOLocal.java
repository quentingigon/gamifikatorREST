package gamifikator.services;

import gamifikator.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserDAOLocal extends IGenericDAO<User>{

	User findByEmail(String email) throws Exception;

	boolean isValidUser(String email, String password) throws Exception;

	List getAllUsers();

}
