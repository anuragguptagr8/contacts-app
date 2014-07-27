package com.itechart.training.tsvilik.contactsapp.bl;

import java.util.List;

import com.itechart.training.tsvilik.contactsapp.entities.Contact;

public interface ContactManager extends EntityManager<Contact, Integer> {

	List<Contact> getBatch(int batchSize, int batchNumber) throws ModelException;
	
}
