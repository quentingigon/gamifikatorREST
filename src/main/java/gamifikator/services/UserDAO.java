package gamifikator.services;

import gamifikator.model.User;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserDAO extends GenericDAO implements UserDAOLocal {

	@Override
	public boolean create(User user) throws Exception {
		if (user.getEmail() == null)
			throw new Exception("email can not be null !");
		em.persist(user);
		em.flush();
		return true;
	}

	@Override
	public void update(User user) {
		findByEmail(user.getEmail());
		em.merge(user);
	}

	@Override
	public void delete(User user) {
		if (!em.contains(user)) {
			user = findByEmail(user.getEmail());
		}
		em.remove(user);
	}

	@Override
	public long count() {
		return (long) em.createQuery("Select count(t) from " + User.class.getSimpleName() + " t").getSingleResult();
	}

	@Override
	public User findById(User id) {
		return em.find(User.class, id);
	}

	public User findByEmail(String email) {
		return em.find(User.class, email);
	}

	@Override
	public boolean isValidUser(String email) {
		return em.find(User.class, email) != null;
	}

	@Override
	public List getAllUsers() {
		return em.createQuery("SELECT u FROM " + User.class.getSimpleName() + " u").getResultList();
	}
}