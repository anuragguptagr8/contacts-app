package com.itechart.training.tsvilik.contactsapp.dal.dao;

import java.util.List;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;

public interface IdDao<K> {
	List<K> getAllIds() throws DataAccessException;
}
