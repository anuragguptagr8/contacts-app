package com.itechart.training.tsvilik.contactsapp.dal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NullableHelper {
	public static Integer getInt(String field, ResultSet rs)
			throws SQLException {
		return rs.getObject(field) == null ? null : rs.getInt(field);
	}
	
	public static void setInt(PreparedStatement statement, int parameterIndex, Integer value)
			throws SQLException {
		if (value == null) {		
			statement.setNull(parameterIndex, java.sql.Types.INTEGER);
		} else {
			statement.setInt(parameterIndex, value);
		}
	}
	
	public static Boolean getBool(String field, ResultSet rs)
			throws SQLException {
		return rs.getObject(field) == null ? null : rs.getBoolean(field);
	}
	
	public static void setBool(PreparedStatement statement, int parameterIndex, Boolean value)
			throws SQLException {
		if (value == null) {		
			statement.setNull(parameterIndex, java.sql.Types.BIT);
		} else {
			statement.setBoolean(parameterIndex, value);
		}
	}
}
