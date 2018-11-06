package mongoconnection.servlets;

import mongoconnection.dao.MongoConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MongoConnectionServlet implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.init();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		MongoConnection conn = MongoConnection.getInstance();
		conn.close();
	}

}