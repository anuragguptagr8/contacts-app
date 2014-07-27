package com.itechart.training.tsvilik.contactsapp.bl;

import java.util.List;

import com.itechart.training.tsvilik.contactsapp.entities.Identifiable;

public interface EntityManager<T extends Identifiable<K>, K> {
	List<T> getAll() throws ModelException;

	T getByKey(K key) throws ModelException;
	
	T save(T entity) throws ModelException;

	void remove(T entity) throws ModelException;

	void update(T entity) throws ModelException;
	
	int getCount() throws ModelException;
}
