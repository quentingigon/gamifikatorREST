package gamifikator;

import gamifikator.model.User;
import gamifikator.services.UserDAOLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TestUserDAO {

	@EJB
	UserDAOLocal userDao;

	@PostConstruct
	public void testDAO() {
		User user = new User("test", "test", "test", "test");

		userDao.create(user);

		System.out.println("user " + userDao.findByEmail(user.getEmail()));

		userDao.delete(user);

		System.out.println(userDao.findByEmail(user.getEmail()));
	}


}
