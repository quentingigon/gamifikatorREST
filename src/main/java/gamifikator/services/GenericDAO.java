package gamifikator.services;

import gamifikator.model.AbstractObject;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;


public class GenericDAO<T extends AbstractObject<PK>, PK> implements IGenericDAO<T, PK> {

	@PersistenceContext(unitName = "gamifikator")
	EntityManager em;

	private final Class<T> jpaEntityClass;

	public GenericDAO() {
		this.jpaEntityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@PostConstruct
	@Override
	public PK create(T t) {
		em.persist(t);
		em.flush();
		return t.getId();
	}

	@Override
	public void update(T t) {
		findById(t.getId());
		em.merge(t);
	}

	@Override
	public void delete(T t) {
	if (!em.contains(t)) {
		t = findById(t.getId());
	}
		em.remove(t);
	}

	@Override
	public long count() {
		return (long)em.createQuery("Select count(t) from " + jpaEntityClass.getSimpleName() + " t").getSingleResult();	}

	@Override
	public T findById(PK id) {
		T result = em.find(jpaEntityClass, id);
		if (result != null) {
			throw new Error("Entity with id " + id + " not found");
		}
		return em.find(jpaEntityClass, id);
	}
}
