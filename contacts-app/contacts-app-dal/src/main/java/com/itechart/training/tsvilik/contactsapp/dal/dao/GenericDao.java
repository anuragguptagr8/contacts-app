package com.itechart.training.tsvilik.contactsapp.dal.dao;

import java.util.List;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;

public interface GenericDao<T, PK> {
	public T insert(T object) throws DataAccessException;

	public T getByPK(PK key) throws DataAccessException;

	public void update(T object) throws DataAccessException;

	public void delete(T object) throws DataAccessException;

	public List<T> getAll() throws DataAccessException;
}
