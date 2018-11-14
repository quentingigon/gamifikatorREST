package gamifikator.services;

import gamifikator.model.Application;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ApplicationDAOLocal extends IGenericDAO<Application>{

	Application findAppByName(String name) throws Exception;

	List getAllApplicationsOfUserByEmail(String email);
}
