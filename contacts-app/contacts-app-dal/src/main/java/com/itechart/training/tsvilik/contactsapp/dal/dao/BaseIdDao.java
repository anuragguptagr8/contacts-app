package com.itechart.training.tsvilik.contactsapp.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;

public abstract class BaseIdDao<K> implements IdDao<K> {

	protected DataSource dataSource;

	protected abstract String getTableName();

	public BaseIdDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<K> getAllIds() throws DataAccessException {
		List<K> list;
		String sql = "SELECT `id` FROM " + getTableName()
				+ " ORDER BY `id` ASC;";
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

	protected List<K> parseResultSet(ResultSet rs) throws DataAccessException {
		List<K> result = new ArrayList<K>();
		try {
			while (rs.next()) {
				result.add((K) rs.getObject(1));
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		return result;
	}

}
