package com.itechart.training.tsvilik.contactsapp.bl.simple;

import java.util.List;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.AbstractEntityManager;
import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.bl.PhoneNumberManager;
import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.dao.PhoneNumberDao;
import com.itechart.training.tsvilik.contactsapp.entities.PhoneNumber;

public class SimplePhoneNumberManager extends
		AbstractEntityManager<PhoneNumber, Integer> implements
		PhoneNumberManager {

	private static Logger logger = Logger
			.getLogger(SimplePhoneNumberManager.class);

	@Override
	protected PhoneNumberDao getDao() {
		return DaoFactoryProvider.getFactory().getPhoneNumberDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	public List<PhoneNumber> getContactNumbers(int contactId)
			throws ModelException {
		try {
			return getDao().getContactNumbers(contactId);
		} catch (DataAccessException e) {
			getLogger().error(
					"Failed to get contact's " + contactId + " numbers.", e);
			throw new ModelException("Failed to get contact's " + contactId
					+ " numbers.", e);
		}
	}

	@Override
	public void updateNumbers(List<PhoneNumber> numbers) throws ModelException {
		try {
			for (PhoneNumber number : numbers) {
				if (number.getId() == null) {
					getDao().insert(number);
				} else if (number.getId() < 0) {
					getDao().delete(number.getId() * (-1));
				} else {
					getDao().update(number);
				}
			}
		} catch (DataAccessException e) {
			getLogger().error("Failed to update some numbers.", e);
			throw new ModelException("Failed to update some numbers.", e);
		}

	}

}
