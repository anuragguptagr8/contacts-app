package com.itechart.training.tsvilik.contactsapp.bl.simple;

import java.util.List;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.AbstractEntityManager;
import com.itechart.training.tsvilik.contactsapp.bl.ContactManager;
import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.dal.dao.ContactDao;
import com.itechart.training.tsvilik.contactsapp.entities.Contact;

public class SimpleContactManager extends
		AbstractEntityManager<Contact, Integer> implements ContactManager {

	private static Logger logger = Logger.getLogger(SimpleContactManager.class);

	@Override
	protected ContactDao getDao() {
		return DaoFactoryProvider.getFactory().getContactDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	public List<Contact> getBatch(int batchSize, int batchNumber)
			throws ModelException {
		try {
			return getDao().getBatch(batchSize, batchNumber);
		} catch (Exception e) {
			getLogger().error(
					"Failed to get batch of contacts. Size: " + batchSize
							+ ", number: " + batchNumber, e);
			throw new ModelException("Failed to get batch of contacts. Size: "
					+ batchSize + ", number: " + batchNumber, e);
		}
	}

}
