package com.itechart.training.tsvilik.contactsapp.bl;

import java.util.List;

import com.itechart.training.tsvilik.contactsapp.entities.PhoneNumber;


public interface PhoneNumberManager extends EntityManager<PhoneNumber, Integer> {

	List<PhoneNumber> getContactNumbers(int contactId) throws ModelException;
	
	void updateNumbers(List<PhoneNumber> numbers) throws ModelException;
}
