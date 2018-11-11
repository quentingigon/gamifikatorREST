package gamifikator.services;

import com.mongodb.DBObject;
import gamifikator.model.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserDAO extends BasicDAO<User, ObjectId> implements UserDAOLocal {

	@EJB
	private UserDAOLocal userDAO;

	public UserDAO(Datastore ds) {
		super(ds);
	}

	@Override
	public User getUser(String email) {
		MongoConnection conn = MongoConnection.getInstance();

		Query<User> query = conn.getDatastore().createQuery(User.class)
			.field("email").equal(email);

		return findOne(query);
	}

	@Override
	public boolean addUser(User user) {
		MongoConnection conn = MongoConnection.getInstance();

		DBObject tmp = conn.getMorphia().toDBObject(user);

		getCollection().insert(tmp);

		Query<User> query = conn.getDatastore().createQuery(User.class)
			.field("email").equal(user.getEmail())
			.field("password").equal(user.getPassword());

		return exists(query);
	}
}