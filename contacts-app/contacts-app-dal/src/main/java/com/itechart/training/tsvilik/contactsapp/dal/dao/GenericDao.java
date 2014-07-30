package com.itechart.training.tsvilik.contactsapp.dal.dao;

import java.util.List;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;

public interface GenericDao<T, K> {
	public T insert(T object) throws DataAccessException;

	public T getByKey(K key) throws DataAccessException;

	public void update(T object) throws DataAccessException;

	public void delete(K key) throws DataAccessException;

	public List<T> getAll() throws DataAccessException;
	 
	public int getCount() throws DataAccessException;
}
