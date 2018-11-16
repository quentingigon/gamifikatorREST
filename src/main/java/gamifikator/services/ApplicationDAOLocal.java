package gamifikator.services;

import gamifikator.model.Application;

import javax.ejb.Local;
import java.util.List;

/**
 * Local interface for our application DAO
 *
 * */
@Local
public interface ApplicationDAOLocal extends IGenericDAO<Application>{

	/**
	 * Find an app by it's name
	 *
	 * @param name name application to find
	 *
	 * @return true if valid, false otherwise
	 *
	 * */
	Application findAppByName(String name) throws Exception;

	/**
	 * Get all apps of certain user
	 *
	 * @param email Email of user to get the apps from
	 *
	 * @return List of apps
	 *
	 * */

	List findAppsOfUserPages(String email, int pageSize, int pageIndex);

	public List getAllApplicationsOfUserByCreator(String creator);
}
