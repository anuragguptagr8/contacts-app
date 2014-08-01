package com.itechart.training.tsvilik.contactsapp.bl.simple;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.AbstractIdEntityManager;
import com.itechart.training.tsvilik.contactsapp.bl.PhoneTypeManager;
import com.itechart.training.tsvilik.contactsapp.dal.dao.PhoneTypeDao;

public class SimplePhoneTypeManager extends AbstractIdEntityManager<Integer>
		implements PhoneTypeManager {
	private static Logger logger = Logger.getLogger(SimpleCountryManager.class);

	@Override
	protected PhoneTypeDao getDao() {
		return DaoFactoryProvider.getFactory().getPhoneTypeDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}
}