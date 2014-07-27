package com.itechart.training.tsvilik.contactsapp.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.Identifiable;

public abstract class BaseDbDao<T extends Identifiable<K>, K> implements
		GenericDao<T, K> {
	protected DataSource dataSource;

	protected abstract String getSelectQuery();

	protected abstract String getInsertQuery();

	protected abstract String getUpdateQuery();

	protected abstract String getDeleteQuery();

	protected abstract String getCountQuery();
	
	protected abstract List<T> parseResultSet(ResultSet rs)
			throws DataAccessException;

	protected abstract void prepareStatementForInsert(
			PreparedStatement statement, T object) throws DataAccessException;

	protected abstract void prepareStatementForUpdate(
			PreparedStatement statement, T object) throws DataAccessException;

	public BaseDbDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public T getByKey(K key) throws DataAccessException {
		List<T> list;
		String sql = getSelectQuery();
		sql += " WHERE id = ?";
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, key);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
			statement.close();
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
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
			statement.close();
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
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			prepareStatementForInsert(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new DataAccessException(
						"More then 1 record modified on insert: " + count);
			}
			statement.close();

			sql = getSelectQuery() + " WHERE id = last_insert_id();";
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			List<T> list = parseResultSet(rs);
			if ((list == null) || (list.size() != 1)) {
				throw new DataAccessException(
						"Exception on findByPK: new data SELECT error.");
			}
			persistInstance = list.iterator().next();
			statement.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		return persistInstance;
	}

	public void update(T object) throws DataAccessException {
		String sql = getUpdateQuery();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			prepareStatementForUpdate(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new DataAccessException(
						"More then 1 record modified on update: " + count);
			}
			statement.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void delete(T object) throws DataAccessException {
		String sql = getDeleteQuery();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, object.getId());
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

	public int getCount() throws DataAccessException {
		String sql = getCountQuery();
		int count = 0;
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			statement.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		return count;
	}
}
