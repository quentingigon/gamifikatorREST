package gamifikator.services;

import gamifikator.model.Application;

import javax.ejb.Stateless;

@Stateless
public class ApplicationDAO extends GenericDAO implements ApplicationDAOLocal {

	public ApplicationDAO() {}

	@Override
	public boolean create(Application app) throws Exception {
		em.persist(app);
		em.flush();
		return true;
	}

	@Override
	public void update(Application app) throws Exception {
		findById(app);
		em.merge(app);
	}

	@Override
	public void delete(Application app) throws Exception {
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
	public Application findById(Application id) throws Exception {
		Application result = em.find(Application.class, id);
		if (result == null) {
			throw new Exception("Entity with id " + id + " not found");
		}
		return em.find(Application.class, id);
	}

	@Override
	public Application findAppByName(String name) throws Exception {
		if (em.find(Application.class, name) == null) {
			throw new Exception("Application with name " + name + " not found");
		}
		else
			return em.find(Application.class, name);
	}
}
