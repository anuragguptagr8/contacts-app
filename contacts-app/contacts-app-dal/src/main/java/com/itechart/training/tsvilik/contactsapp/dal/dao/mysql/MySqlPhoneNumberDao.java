package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.dao.BaseDbDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.NullableHelper;
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
				phoneNumber.setId(NullableHelper.getInt("id", rs));
				phoneNumber.setCountryCode(NullableHelper.getInt("country_code", rs));
				phoneNumber.setOperatorCode(NullableHelper.getInt("operator_code", rs));
				phoneNumber.setNumber(NullableHelper.getInt("number", rs));
				phoneNumber.setTypeId(NullableHelper.getInt("type_id", rs));
				phoneNumber.setContactId(NullableHelper.getInt("contact_id", rs));
				phoneNumber.setComment(rs.getString("comment"));
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
			NullableHelper.setInt(statement, 1, object.getCountryCode());
			NullableHelper.setInt(statement, 2, object.getOperatorCode());
			NullableHelper.setInt(statement, 3, object.getNumber());
			NullableHelper.setInt(statement, 4, object.getTypeId());
			statement.setString(5, object.getComment());
			NullableHelper.setInt(statement, 6, object.getContactId());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			PhoneNumber object) throws DataAccessException {
		try {
			NullableHelper.setInt(statement, 1, object.getCountryCode());
			NullableHelper.setInt(statement, 2, object.getOperatorCode());
			NullableHelper.setInt(statement, 3, object.getNumber());
			NullableHelper.setInt(statement, 4, object.getTypeId());
			statement.setString(5, object.getComment());
			NullableHelper.setInt(statement, 6, object.getContactId());
			NullableHelper.setInt(statement, 7, object.getId());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public List<PhoneNumber> getContactNumbers(int contactId) throws DataAccessException {
		List<PhoneNumber> list;
		String sql = getSelectQuery() + "WHERE `contact_id` = ?";
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, contactId);
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
			statement.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		return list;
	}
}
