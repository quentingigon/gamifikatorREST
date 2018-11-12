package gamifikator.services;

import gamifikator.model.User;

import javax.ejb.Local;

@Local
public interface UserDAOLocal extends IGenericDAO<User>{

	User findByEmail(String email) throws Exception;

}
