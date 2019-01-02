package gamifikator.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Generic DAO containing the entity manager for database access
 *
 * */
public class GenericDAO {

	@PersistenceContext
	EntityManager em;

}
