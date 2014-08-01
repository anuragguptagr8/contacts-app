package com.itechart.training.tsvilik.contactsapp.bl.simple;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.AbstractIdEntityManager;
import com.itechart.training.tsvilik.contactsapp.bl.CountryManager;
import com.itechart.training.tsvilik.contactsapp.dal.dao.CountryDao;

public class SimpleCountryManager extends AbstractIdEntityManager<String>
		implements CountryManager {
	private static Logger logger = Logger.getLogger(SimpleCountryManager.class);

	@Override
	protected CountryDao getDao() {
		return DaoFactoryProvider.getFactory().getCountryDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}
}
