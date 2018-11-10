package gamifikator.services;

import gamifikator.model.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserDAO extends BasicDAO<User, ObjectId> implements UserDAOLocal {

	public UserDAO(Datastore ds) {
		super(ds);
	}

	@Override
	public User getUser() {
		return null;
	}

	@Override
	public boolean addUser() {
		return false;
	}
}