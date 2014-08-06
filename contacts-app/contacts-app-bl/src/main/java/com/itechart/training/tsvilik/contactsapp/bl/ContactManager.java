package com.itechart.training.tsvilik.contactsapp.bl;

import java.util.List;

import com.itechart.training.tsvilik.contactsapp.entities.Contact;
import com.itechart.training.tsvilik.contactsapp.entities.SearchData;

public interface ContactManager extends EntityManager<Contact, Integer> {

	List<Contact> getBatch(int batchSize, int batchNumber) throws ModelException;
	
	List<Contact> search(SearchData data, int batchSize, int batchNumber) throws ModelException;
	
	int getSearchResultsCount(SearchData data) throws ModelException;
}
