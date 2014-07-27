package com.itechart.training.tsvilik.contactsapp.bl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.Identifiable;
import com.itechart.training.tsvilik.contactsapp.dal.dao.GenericDao;

public abstract class AbstractEntityManager<T extends Identifiable<K>, K>
		implements EntityManager<T, K> {

	abstract protected Logger getLogger();

	abstract protected GenericDao<T, K> getDao();

	@Override
	public List<T> getAll() throws ModelException {
		try {
			return getDao().getAll();
		} catch (DataAccessException e) {
			getLogger().error("Failed to get all entities", e);
			throw new ModelException("Failed to get all entities", e);
		}
	}

	@Override
	public T getByKey(K key) throws ModelException {
		try {
			return getDao().getByKey(key);
		} catch (DataAccessException e) {
			getLogger().error("Failed to get an entity by key: " + key, e);
			throw new ModelException("Failed to get an entity by key: " + key,
					e);
		}
	}

	@Override
	public T save(T entity) throws ModelException {
		try {
			return getDao().insert(entity);
		} catch (DataAccessException e) {
			getLogger().error("Failed to save entity.", e);
			throw new ModelException("Failed to save entity.", e);
		}
	}

	@Override
	public void remove(T entity) throws ModelException {
		try {
			getDao().delete(entity);
		} catch (DataAccessException e) {
			getLogger().error(
					"Failed to remove the entity, id: " + entity.getId(), e);
			throw new ModelException("Failed to remove the entity, id: "
					+ entity.getId(), e);
		}
	}

	@Override
	public void update(T entity) throws ModelException {
		try {
			getDao().update(entity);
		} catch (DataAccessException e) {
			getLogger().error(
					"Failed to update the entity, id: " + entity.getId(), e);
			throw new ModelException("Failed to update the entity, id: "
					+ entity.getId(), e);
		}

	}

	@Override
	public int getCount() throws ModelException {
		try {
			return getDao().getCount();
		} catch (DataAccessException e) {
			getLogger().error("Failed to get count of entities.", e);
			throw new ModelException("Failed to get count of entities.", e);
		}
	}
}
