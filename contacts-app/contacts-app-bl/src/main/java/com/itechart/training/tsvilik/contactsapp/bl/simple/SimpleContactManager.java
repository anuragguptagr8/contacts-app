package com.itechart.training.tsvilik.contactsapp.bl.simple;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.AbstractEntityManager;
import com.itechart.training.tsvilik.contactsapp.bl.ContactManager;
import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.dao.ContactDao;
import com.itechart.training.tsvilik.contactsapp.entities.Contact;
import com.itechart.training.tsvilik.contactsapp.entities.SearchData;

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

	@Override
	public List<Contact> search(SearchData data, int batchSize, int batchNumber)
			throws ModelException {
		try {
			return getDao().search(data, batchSize, batchNumber);
		} catch (Exception e) {
			getLogger().error(
					"Failed to get search results. Batch size: " + batchSize
							+ ", number: " + batchNumber, e);
			throw new ModelException(
					"Failed to get search results. Batch size: " + batchSize
							+ ", number: " + batchNumber, e);
		}
	}

	@Override
	public int getSearchResultsCount(SearchData data) throws ModelException {
		try {
			return getDao().getSearchResultsCount(data);
		} catch (DataAccessException e) {
			getLogger().error("Failed to get count of search results.", e);
			throw new ModelException("Failed to get count of search results.",
					e);
		}
	}

	@Override
	public List<Contact> getByBirthday(Date birthday) throws ModelException {
		try {
			return getDao().getByBirthday(birthday);
		} catch (Exception e) {
			getLogger().error("Failed to get contacts by birthday " + birthday,
					e);
			throw new ModelException("Failed to get contacts by birthday "
					+ birthday, e);
		}
	}

}
