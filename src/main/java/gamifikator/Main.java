package gamifikator;

import com.mongodb.DBObject;
import gamifikator.model.User;
import gamifikator.services.AppsDeployer;
import gamifikator.services.MongoConnection;
import gamifikator.services.UserDAO;

public class Main {

	public static void main(String...args) {

		/* Testing DB */
		UserDAO userDao;

		MongoConnection conn = MongoConnection.getInstance();
		userDao = new UserDAO(conn.getDatastore());

		User user = new User("test", "test", "test", "test");
		DBObject tmp = conn.getMorphia().toDBObject(user);

		userDao.addUser(user);

		//userDao.getCollection().insert(tmp);

		System.out.println(userDao.getUser("test").getFirstName());

		/*Testing appsDeployer*/
		AppsDeployer appsDeployer = new AppsDeployer();
		appsDeployer.undeployUserApp("prout56487");
	}
}
