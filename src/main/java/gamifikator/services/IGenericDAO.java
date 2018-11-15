package gamifikator.services;

import javax.ejb.Local;

@Local
public interface IGenericDAO<T> {

	/**
	 * Create T object in database
	 *
	 * @param t object to insert in database
	 *
	 * @return true if created, false otherwise
	 *
	 * */
	boolean create(T t) throws Exception;

	/**
	 * update T object in database
	 *
	 * @param t object to update
	 *
	 * @return true if updated, false otherwise
	 *
	 * */
	void update(T t) throws Exception;

	/**
	 * Delete T object in database
	 *
	 * @param t object to delete
	 *
	 * */
	void delete(T t) throws Exception;

	/**
	 * Count T objects in database
	 *
	 * @return number of elements
	 *
	 * */
	long count();

	/**
	 * Find object by ID
	 *
	 * @param id id of object to find
	 *
	 * @return found object or null otherwise
	 *
	 * */
	T findById(T id) throws Exception;

}
