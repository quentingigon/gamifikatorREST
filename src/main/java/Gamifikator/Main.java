package Gamifikator;

import Gamifikator.mongoconnection.dao.MongoConnection;
import Gamifikator.mongoconnection.dao.UserDAO;
import Gamifikator.mongoconnection.models.UserDO;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class Main {

	public static void main(String...args) {

		UserDAO userDao;

		MongoConnection conn = MongoConnection.getInstance();
		userDao = new UserDAO(conn.getDatastore());

		UserDO user = new UserDO("test", "test");
		DBObject tmp = conn.getMorphia().toDBObject(user);

		WriteResult wResult = userDao.getCollection().insert(tmp);

		System.out.println(wResult.getUpsertedId());

		System.out.println(userDao.find().asList().get(0).getFirstName());
	}


}
