package gamifikator;

import com.mongodb.DBObject;
import gamifikator.model.User;
import gamifikator.services.AppsDeployer;
import gamifikator.services.MongoConnection;
import gamifikator.services.UserDAO;

public class Main {

	public static void main(String...args) {

		TestUserDAO test = new TestUserDAO();

		System.out.println(userDao.getUser("test").getFirstName());

		test.testDAO();

	}
}
