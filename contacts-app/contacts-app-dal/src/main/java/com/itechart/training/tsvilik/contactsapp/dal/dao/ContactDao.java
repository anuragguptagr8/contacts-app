package com.itechart.training.tsvilik.contactsapp.dal.dao;

import java.util.List;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.transfer_objects.Contact;

public interface ContactDao extends GenericDao<Contact, Integer>{
	List<Contact> getBatch(int batchSize, int batchNumber)
			throws DataAccessException;
}
