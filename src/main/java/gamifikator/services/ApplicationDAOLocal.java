package gamifikator.services;

import gamifikator.model.Application;

import javax.ejb.Local;

@Local
public interface ApplicationDAOLocal {

	Application getApp(String name);

	boolean addApp(Application app);

	boolean deleteApp(String name);
}
