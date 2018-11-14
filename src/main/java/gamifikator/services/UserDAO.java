package gamifikator.services;

import gamifikator.model.User;

import javax.ejb.Stateless;

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
	public void update(User user) throws Exception {
		findById(user);
		em.merge(user);
	}

	@Override
	public void delete(User user) throws Exception {
		if (!em.contains(user)) {
			user = findById(user);
		}
		em.remove(user);
	}

	@Override
	public long count() {
		return (long) em.createQuery("Select count(t) from " + User.class.getSimpleName() + " t").getSingleResult();
	}

	@Override
	public User findById(User id) throws Exception {
		User result = em.find(User.class, id);
		if (result == null) {
			throw new Exception("Entity with id " + id + " not found");
		}
		return em.find(User.class, id);
	}

	public User findByEmail(String email) throws Exception {

		if (em.find(User.class, email) == null) {
			throw new Exception("User with email " + email + " not found");
		}
		else
			return em.find(User.class, email);
	}

	@Override
	public boolean isValidUser(String email, String password) throws Exception {
		User user = em.find(User.class, email);
		if (user == null) {
			throw new Exception("User with email " + email + " not found");
		}
		else if (!user.getPassword().equals(password))
			throw new Exception("Bad password");
		else
			return true;
	}
}