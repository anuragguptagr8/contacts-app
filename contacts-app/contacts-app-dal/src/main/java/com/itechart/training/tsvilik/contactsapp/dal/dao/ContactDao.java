package com.itechart.training.tsvilik.contactsapp.dal.dao;

import java.util.Date;
import java.util.List;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.entities.Contact;
import com.itechart.training.tsvilik.contactsapp.entities.SearchData;

public interface ContactDao extends GenericDao<Contact, Integer> {
	List<Contact> getBatch(int batchSize, int batchNumber)
			throws DataAccessException;

	List<Contact> search(SearchData data, int batchSize, int batchNumber)
			throws DataAccessException;
	
	int getSearchResultsCount(SearchData data) throws DataAccessException;
	
	List<Contact> getByBirthday(Date birthday) throws DataAccessException;
}
