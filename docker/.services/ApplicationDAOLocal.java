package gamifikator.services;

import gamifikator.model.Application;

import javax.ejb.Local;

@Local
public interface ApplicationDAOLocal {

	Application getApp();

	boolean addApp();
}
