package gamifikator.services;

import javax.ejb.Local;

@Local
public interface IGenericDAO<T> {

	boolean create(T t) throws Exception;

	void update(T t);

	void delete(T t);

	long count();

	T findById(T id);

}
