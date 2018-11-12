package gamifikator.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericDAO {

	@PersistenceContext
	EntityManager em;

}
