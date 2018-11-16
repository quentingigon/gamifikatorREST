package gamifikator.services;

import gamifikator.model.Application;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * DAO for applications
 *
 * */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ApplicationDAO extends GenericDAO implements ApplicationDAOLocal {

	@Override
	public boolean create(Application app) {

		em.persist(app);
		em.flush();
		return true;

	}

	@Override
	public void update(Application app) {
		findById(app);
		em.merge(app);
	}

	@Override
	public void delete(Application app) {
		if (!em.contains(app)) {
			app = findById(app);
		}
		em.remove(app);
	}

	@Override
	public long count() {
		return (long) em.createQuery("Select count(t) from " + Application.class.getSimpleName() + " t").getSingleResult();
	}

	@Override
	public Application findById(Application id) {
		return em.find(Application.class, id);
	}

	@Override
	public Application findAppByName(String name) {
		return em.find(Application.class, name);
	}

	@Override
	public List getAllApplicationsOfUserByCreator(String creator) {
		return em.createQuery("SELECT * FROM Application WHERE Application.creator=" + creator).getResultList();
	}

	@Override
	public List findAppsOfUserPages(String email, int pageSize, int pageIndex) {
		return em.createQuery("Select * FROM Application WHERE owner_email = " + email)
			.setMaxResults(pageSize)
			.setFirstResult(pageIndex * pageSize)
			.getResultList();
	}
}
