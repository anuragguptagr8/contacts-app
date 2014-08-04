package com.itechart.training.tsvilik.contactsapp.dal.dao;

import java.util.List;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.entities.PhoneNumber;

public interface PhoneNumberDao extends GenericDao<PhoneNumber, Integer>{

	List<PhoneNumber> getContactNumbers(int contactId) throws DataAccessException;
	
}
