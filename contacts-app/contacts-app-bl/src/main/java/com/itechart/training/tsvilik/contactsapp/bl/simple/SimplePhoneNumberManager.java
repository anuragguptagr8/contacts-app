package com.itechart.training.tsvilik.contactsapp.bl.simple;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.AbstractEntityManager;
import com.itechart.training.tsvilik.contactsapp.bl.PhoneNumberManager;
import com.itechart.training.tsvilik.contactsapp.dal.dao.PhoneNumberDao;
import com.itechart.training.tsvilik.contactsapp.dal.transfer_objects.PhoneNumber;

public class SimplePhoneNumberManager extends
		AbstractEntityManager<PhoneNumber, Integer> implements PhoneNumberManager {

	private static Logger logger = Logger.getLogger(SimplePhoneNumberManager.class);

	@Override
	protected PhoneNumberDao getDao() {
		return DaoFactoryProvider.getFactory().getPhoneNumberDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

}
