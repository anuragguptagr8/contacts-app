package com.itechart.training.tsvilik.contactsapp.dal.dao;

import java.util.List;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.entities.Attachment;

public interface AttachmentDao extends GenericDao<Attachment, Integer>{

	List<Attachment> getContactAttachments(int contactId) throws DataAccessException;
	
}
