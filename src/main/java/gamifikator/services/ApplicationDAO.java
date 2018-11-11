package gamifikator.services;

import com.mongodb.DBObject;
import gamifikator.model.Application;
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
public class ApplicationDAO extends BasicDAO<Application, ObjectId> implements ApplicationDAOLocal {

	@EJB
	ApplicationDAOLocal appDAO;

	public ApplicationDAO(Datastore ds) {
		super(ds);
	}

	@Override
	public Application getApp(String name) {
		MongoConnection conn = MongoConnection.getInstance();

		Query<Application> query = conn.getDatastore().createQuery(Application.class)
			.field("name").equal(name);

		return findOne(query);
	}

	@Override
	public boolean addApp(Application app) {
		MongoConnection conn = MongoConnection.getInstance();

		DBObject tmp = conn.getMorphia().toDBObject(app);

		getCollection().insert(tmp);

		Query<Application> query = conn.getDatastore().createQuery(Application.class)
			.field("name").equal(app.getName())
			.field("creator").equal(app.getCreator());

		return exists(query);
	}
}
