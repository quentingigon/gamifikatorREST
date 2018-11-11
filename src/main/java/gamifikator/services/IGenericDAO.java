package gamifikator.services;

import gamifikator.model.AbstractObject;

import javax.ejb.Local;

@Local
public interface IGenericDAO<T extends AbstractObject, PK> {

	PK create(T t);

	void update(T t);

	void delete(T t);

	long count();

	T findById(PK id);

}
