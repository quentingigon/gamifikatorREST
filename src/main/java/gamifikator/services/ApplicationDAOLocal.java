package gamifikator.services;

import gamifikator.model.Application;

import javax.ejb.Local;

@Local
public interface ApplicationDAOLocal extends IGenericDAO<Application>{

	Application findAppByName(String name) throws Exception;
}
