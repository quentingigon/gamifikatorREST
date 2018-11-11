package gamifikator.services;

import gamifikator.model.User;

import javax.ejb.Local;

@Local
public interface UserDAOLocal {

	User getUser(String email);

	boolean addUser(User user);

}
