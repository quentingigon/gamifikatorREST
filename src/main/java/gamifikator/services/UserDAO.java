package gamifikator.services;

import gamifikator.model.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;


/**
 * DAO for users.
 *
 * */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
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
		if (findByEmail(user.getEmail()) != null) {
			em.merge(user);
		}
		throw new Exception("Huge error");
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
		// return (User) em.createQuery("SELECT u FROM User u where u.email = " + "'" + email + "'");
		return em.find(User.class, email);
	}

	@Override
	public boolean isValidUser(String email, String username) {

		return findByEmail(email) != null;
				//em.createQuery("SELECT u FROM User u where u.username =" + username) != null;
	}

	@Override
	public List getAllUsers() {
		return em.createQuery("SELECT u FROM User u").getResultList();
	}

	@Override
	public List getUsersPages(int pageSize, int pageIndex) {
		return em.createQuery("SELECT u FROM User u")
			.setMaxResults(pageSize)
			.setFirstResult(pageIndex * pageSize)
			.getResultList();
	}
}