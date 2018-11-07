package gamifikator;

import com.mongodb.DBObject;
import gamifikator.mongoconnection.dao.MongoConnection;
import gamifikator.mongoconnection.dao.UserDAO;
import gamifikator.mongoconnection.models.UserDO;

public class Main {

	public static void main(String...args) {

		/* Testing DB */
		UserDAO userDao;

		MongoConnection conn = MongoConnection.getInstance();
		userDao = new UserDAO(conn.getDatastore());

		UserDO user = new UserDO("test", "test");
		DBObject tmp = conn.getMorphia().toDBObject(user);

		userDao.getCollection().insert(tmp);

		System.out.println(userDao.find().asList().get(0).getFirstName());
	}
}
