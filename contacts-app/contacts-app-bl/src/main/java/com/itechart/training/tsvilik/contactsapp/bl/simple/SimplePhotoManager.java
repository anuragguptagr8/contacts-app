package com.itechart.training.tsvilik.contactsapp.bl.simple;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.AbstractEntityManager;
import com.itechart.training.tsvilik.contactsapp.bl.PhotoManager;
import com.itechart.training.tsvilik.contactsapp.dal.dao.PhotoDao;
import com.itechart.training.tsvilik.contactsapp.entities.Photo;

public class SimplePhotoManager extends
		AbstractEntityManager<Photo, Integer> implements PhotoManager {

	private static Logger logger = Logger.getLogger(SimplePhotoManager.class);

	@Override
	protected PhotoDao getDao() {
		return DaoFactoryProvider.getFactory().getPhotoDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

}
