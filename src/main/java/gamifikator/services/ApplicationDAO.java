package gamifikator.services;

import gamifikator.model.Application;

import javax.ejb.Stateless;

@Stateless
public class ApplicationDAO implements ApplicationDAOLocal {

	public ApplicationDAO() {}

	@Override
	public Application getApp(String name) {
		return null;
	}

	@Override
	public boolean addApp(Application app) {
		return false;
	}

	@Override
	public boolean deleteApp(String name) {
		return false;
	}
}
