package com.itechart.training.tsvilik.contactsapp.bl.simple;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.AbstractIdEntityManager;
import com.itechart.training.tsvilik.contactsapp.bl.RelationshipStatusManager;
import com.itechart.training.tsvilik.contactsapp.dal.dao.RelationshipStatusDao;

public class SimpleRelationshipStatusManager extends
		AbstractIdEntityManager<Integer> implements RelationshipStatusManager {
	private static Logger logger = Logger
			.getLogger(SimpleRelationshipStatusManager.class);

	@Override
	protected RelationshipStatusDao getDao() {
		return DaoFactoryProvider.getFactory().getRelationshipStatusDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}
}
