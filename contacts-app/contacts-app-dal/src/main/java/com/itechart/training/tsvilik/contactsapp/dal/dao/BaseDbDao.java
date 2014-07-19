package com.itechart.training.tsvilik.contactsapp.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.Identified;

public abstract class BaseDbDao<T extends Identified<PK>, PK>
		implements GenericDao<T, PK> {
	private Connection connection;

	public abstract String getSelectQuery();

	public abstract String getInsertQuery();

	public abstract String getUpdateQuery();

	public abstract String getDeleteQuery();

	protected abstract List<T> parseResultSet(ResultSet rs)
			throws DataAccessException;

	protected abstract void prepareStatementForInsert(
			PreparedStatement statement, T object) throws DataAccessException;

	protected abstract void prepareStatementForUpdate(
			PreparedStatement statement, T object) throws DataAccessException;

	public BaseDbDao(Connection connection) {
		this.connection = connection;
	}

	public T getByPK(PK key) throws DataAccessException {
		List<T> list;
		String sql = getSelectQuery();
		sql += " WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setObject(1, key);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		if (list == null || list.isEmpty()) {
			return null;
		}
		if (list.size() > 1) {
			throw new DataAccessException("Received more than one record.");
		}
		return list.iterator().next();
	}

	public List<T> getAll() throws DataAccessException {
		List<T> list;
		String sql = getSelectQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		return list;
	}

	public T insert(T object) throws DataAccessException {
		if (object.getId() != null) {
			throw new DataAccessException("Object is already persist.");
		}
		T persistInstance;
		String sql = getInsertQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareStatementForInsert(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new DataAccessException(
						"More then 1 record modified on insert: " + count);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		sql = getSelectQuery() + " WHERE id = last_insert_id();";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			List<T> list = parseResultSet(rs);
			if ((list == null) || (list.size() != 1)) {
				throw new DataAccessException(
						"Exception on findByPK new persist data.");
			}
			persistInstance = list.iterator().next();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		return persistInstance;
	}

	public void update(T object) throws DataAccessException {
		String sql = getUpdateQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			prepareStatementForUpdate(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new DataAccessException(
						"More then 1 record modified on update: " + count);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void delete(T object) throws DataAccessException {
		String sql = getDeleteQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			try {
				statement.setObject(1, object.getId());
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new DataAccessException(
						"More then 1 record modified on delete: " + count);
			}
			statement.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
}
