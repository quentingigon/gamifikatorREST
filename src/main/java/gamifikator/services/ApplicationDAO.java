package gamifikator.services;

import gamifikator.model.Application;

import javax.ejb.Stateless;
import java.util.ArrayList;

@Stateless
public class ApplicationDAO extends GenericDAO implements ApplicationDAOLocal {

	@Override
	public boolean create(Application app) {
		if (findAppByName(app.getName()) != null) {
			em.persist(app);
			em.flush();
			return true;
		}
		else
			return false;

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
	public ArrayList<Application> getAllApplicationsOfUserByEmail(String email) {
		return (ArrayList<Application>) em.createQuery("Select * from " + Application.class.getSimpleName() + " where owner=" + email).getResultList();
	}
}
