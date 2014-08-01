package com.itechart.training.tsvilik.contactsapp.bl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.dao.IdDao;

public abstract class AbstractIdEntityManager<K> implements IdEntityManager<K> {

	abstract protected Logger getLogger();

	abstract protected IdDao<K> getDao();

	@Override
	public List<K> getAllIds() throws ModelException {
		try {
			return getDao().getAllIds();
		} catch (DataAccessException e) {
			getLogger().error("Failed to get list of ids.", e);
			throw new ModelException("Failed to get list of ids.", e);
		}
	}
}
