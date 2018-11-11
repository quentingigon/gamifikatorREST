package gamifikator;

import gamifikator.model.User;
import gamifikator.services.UserDAO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class TestUserDAO {

	@EJB
	UserDAO userDao;

	@PostConstruct
	public void testDAO() {
		User user = new User("test", "test", "test", "test");

		userDao.create(user);

		System.out.println("user " + userDao.findById(user.getId()));

		userDao.delete(user);

		System.out.println(userDao.findById(user.getId()));
	}


}
