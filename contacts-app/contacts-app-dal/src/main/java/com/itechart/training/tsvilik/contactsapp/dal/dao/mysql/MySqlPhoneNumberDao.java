package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.dao.BaseDbDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.PhoneNumberDao;
import com.itechart.training.tsvilik.contactsapp.entities.PhoneNumber;

public class MySqlPhoneNumberDao extends BaseDbDao<PhoneNumber, Integer>
		implements PhoneNumberDao {
	protected String numbersTable = "`contacts-app-dev`.`phone_numbers`";

	public MySqlPhoneNumberDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public String getSelectQuery() {
		return "SELECT `id`, `country_code`, `operator_code`, `number`, "
				+ "`type_id`, `comment`, `contact_id` FROM " + numbersTable;
	}

	@Override
	public String getInsertQuery() {
		return "INSERT INTO " + numbersTable + " (`country_code`, "
				+ "`operator_code`, `number`, `type_id`, `comment`, "
				+ "`contact_id`) VALUES (?, ?, ?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE " + numbersTable + " SET `country_code`=?, "
				+ "`operator_code`=?, `number`=?, `type_id`=?, `comment`=?, "
				+ "`contact_id`=? WHERE `id`=?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM " + numbersTable + " WHERE `id`=?;";
	}
	
	@Override
	protected String getCountQuery() {
		return "SELECT count(*) FROM " + numbersTable;
	}

	@Override
	protected List<PhoneNumber> parseResultSet(ResultSet rs)
			throws DataAccessException {
		ArrayList<PhoneNumber> result = new ArrayList<PhoneNumber>();
		try {
			while (rs.next()) {
				PhoneNumber phoneNumber = new PhoneNumber();
				phoneNumber.setId(rs.getInt("id"));
				phoneNumber.setCountryCode(rs.getInt("country_code"));
				phoneNumber.setOperatorCode(rs.getInt("operator_code"));
				phoneNumber.setNumber(rs.getInt("number"));
				phoneNumber.setTypeId(rs.getInt("type_id"));
				phoneNumber.setComment(rs.getString("comment"));
				phoneNumber.setContactId(rs.getInt("contact_id"));
				result.add(phoneNumber);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			PhoneNumber object) throws DataAccessException {
		try {
			statement.setInt(1, object.getCountryCode());
			statement.setInt(2, object.getOperatorCode());
			statement.setInt(3, object.getNumber());
			statement.setInt(4, object.getTypeId());
			statement.setString(5, object.getComment());
			statement.setInt(6, object.getContactId());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			PhoneNumber object) throws DataAccessException {
		try {
			statement.setInt(1, object.getCountryCode());
			statement.setInt(2, object.getOperatorCode());
			statement.setInt(3, object.getNumber());
			statement.setInt(4, object.getTypeId());
			statement.setString(5, object.getComment());
			statement.setInt(6, object.getContactId());
			statement.setInt(7, object.getId());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
}
