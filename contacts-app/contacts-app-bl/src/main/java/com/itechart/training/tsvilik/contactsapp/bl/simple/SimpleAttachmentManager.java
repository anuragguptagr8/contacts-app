package com.itechart.training.tsvilik.contactsapp.bl.simple;

import java.util.List;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.AbstractEntityManager;
import com.itechart.training.tsvilik.contactsapp.bl.AttachmentManager;
import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.dao.AttachmentDao;
import com.itechart.training.tsvilik.contactsapp.entities.Attachment;

public class SimpleAttachmentManager extends
		AbstractEntityManager<Attachment, Integer> implements AttachmentManager {

	private static Logger logger = Logger
			.getLogger(SimpleAttachmentManager.class);

	@Override
	protected AttachmentDao getDao() {
		return DaoFactoryProvider.getFactory().getAttachmentDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	public List<Attachment> getContactAttachments(int contactId)
			throws ModelException {
		try {
			return getDao().getContactAttachments(contactId);
		} catch (DataAccessException e) {
			getLogger().error(
					"Failed to get contact's " + contactId + " attachments.", e);
			throw new ModelException("Failed to get contact's " + contactId
					+ " numbers.", e);
		}
	}

	@Override
	public void updateAttachments(List<Attachment> attachments) throws ModelException {
		try {
			for (Attachment attachment : attachments) {
				if (attachment.getId() == null) {
					getDao().insert(attachment);
				} else if (attachment.getId() < 0) {
					getDao().delete(attachment.getId() * (-1));
				} else {
					getDao().update(attachment);
				}
			}
		} catch (DataAccessException e) {
			getLogger().error("Failed to update some attachments.", e);
			throw new ModelException("Failed to update some attachments.", e);
		}

	}

}
