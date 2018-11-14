package gamifikator.services;

import javax.ejb.Local;

@Local
public interface IGenericDAO<T> {

	boolean create(T t) throws Exception;

	void update(T t) throws Exception;

	void delete(T t) throws Exception;

	long count();

	T findById(T id) throws Exception;

}
