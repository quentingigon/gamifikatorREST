package gamifikator.services;

import gamifikator.model.Application;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ApplicationDAO extends BasicDAO<Application, ObjectId> implements ApplicationDAOLocal {

	@EJB
	ApplicationDAOLocal appDAO;

	public ApplicationDAO(Datastore ds) {
		super(ds);
	}

	@Override
	public Application getApp(String name) {
		return null;
	}

	@Override
	public boolean addApp(Application app) {
		return false;
	}

	@Override
	public boolean deleteApp(String name) {
		return false;
	}
}
