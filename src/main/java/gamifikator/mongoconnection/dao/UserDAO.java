package gamifikator.mongoconnection.dao;

import gamifikator.mongoconnection.models.UserDO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class UserDAO extends BasicDAO<UserDO, ObjectId> {

	public UserDAO(Datastore ds) {
		super(ds);
	}
}