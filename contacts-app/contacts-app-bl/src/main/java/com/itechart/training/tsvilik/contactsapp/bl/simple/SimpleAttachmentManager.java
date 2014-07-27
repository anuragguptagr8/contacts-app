package com.itechart.training.tsvilik.contactsapp.bl.simple;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.AbstractEntityManager;
import com.itechart.training.tsvilik.contactsapp.bl.AttachmentManager;
import com.itechart.training.tsvilik.contactsapp.dal.dao.AttachmentDao;
import com.itechart.training.tsvilik.contactsapp.dal.transfer_objects.Attachment;

public class SimpleAttachmentManager extends
		AbstractEntityManager<Attachment, Integer> implements AttachmentManager {

	private static Logger logger = Logger.getLogger(SimpleAttachmentManager.class);

	@Override
	protected AttachmentDao getDao() {
		return DaoFactoryProvider.getFactory().getAttachmentDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

}
