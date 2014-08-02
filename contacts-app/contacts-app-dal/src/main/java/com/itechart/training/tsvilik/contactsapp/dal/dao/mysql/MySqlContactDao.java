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
import com.itechart.training.tsvilik.contactsapp.dal.dao.ContactDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.NullableHelper;
import com.itechart.training.tsvilik.contactsapp.entities.Contact;

public class MySqlContactDao extends BaseDbDao<Contact, Integer> implements
		ContactDao {

	protected String contactsTable = "`contacts-app-dev`.`contacts`";

	public MySqlContactDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	protected String getSelectQuery() {

		return "SELECT `id`, `first_name`, `last_name`, `middle_name`, "
				+ "`date_of_birth`,	`is_male`, `citizenship`, "
				+ "`relationship_status_id`, `web_site`, `email`, `company`, "
				+ "`country_id`, `city`, `street_address`, `zip`, `photo_id` "
				+ "FROM " + contactsTable;
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO " + contactsTable + " (`first_name`, `last_name`, "
				+ "`middle_name`, `date_of_birth`, `is_male`, `citizenship`, "
				+ "`relationship_status_id`, `web_site`, `email`, `company`, "
				+ "`country_id`, `city`, `street_address`, `zip`, `photo_id`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE " + contactsTable
				+ " SET `first_name`=?, `last_name`=?,"
				+ " `middle_name`=?, `date_of_birth`=?, `is_male`=?,"
				+ " `citizenship`=?, `relationship_status_id`=?, `web_site`=?,"
				+ " `email`=?, `company`=?, `country_id`=?, `city`=?,"
				+ " `street_address`=?, `zip`=?, `photo_id`=? WHERE `id`=?;";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM " + contactsTable + " WHERE `id`=?;";
	}

	@Override
	protected String getCountQuery() {
		return "SELECT count(*) FROM " + contactsTable;
	}

	@Override
	public List<Contact> getBatch(int batchSize, int batchNumber)
			throws DataAccessException {
		if ((batchSize <= 0) || (batchNumber < 0)) {
			throw new DataAccessException("Wrong parameter values");
		}
		List<Contact> batch;
		String sql = getSelectQuery()
				+ " ORDER BY `last_name`, `first_name` ASC LIMIT ? OFFSET ?";
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, batchSize);
			statement.setInt(2, batchNumber * batchSize);
			ResultSet rs = statement.executeQuery();
			batch = parseResultSet(rs);
			statement.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		return batch;
	}

	@Override
	protected List<Contact> parseResultSet(ResultSet rs)
			throws DataAccessException {
		ArrayList<Contact> result = new ArrayList<Contact>();
		try {
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setId(NullableHelper.getInt("id", rs));
				contact.setFirstName(rs.getString("first_name"));
				contact.setLastName(rs.getString("last_name"));
				contact.setMiddleName(rs.getString("middle_name"));
				contact.setDateOfBirth(rs.getDate("date_of_birth"));
				contact.setIsMale(NullableHelper.getBool("is_male", rs));
				contact.setCitizenship(rs.getString("citizenship"));
				contact.setRelationshipStatusId(NullableHelper.getInt(
						"relationship_status_id", rs));
				contact.setWebsite(rs.getString("web_site"));
				contact.setEmail(rs.getString("email"));
				contact.setCompany(rs.getString("company"));
				contact.setCountry(rs.getString("country_id"));
				contact.setCity(rs.getString("city"));
				contact.setStreet(rs.getString("street_address"));
				contact.setPostalCode(rs.getString("zip"));
				contact.setPhotoId(NullableHelper.getInt("photo_id", rs));
				result.add(contact);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Contact object) throws DataAccessException {
		try {
			java.sql.Date sqlDate = convertDateToSql(object.getDateOfBirth());
			statement.setString(1, object.getFirstName());
			statement.setString(2, object.getLastName());
			statement.setString(3, object.getMiddleName());
			statement.setDate(4, sqlDate);
			NullableHelper.setBool(statement, 5, object.getIsMale());
			statement.setString(6, object.getCitizenship());
			NullableHelper.setInt(statement, 7, object.getRelationshipStatusId());
			statement.setString(8, object.getWebsite());
			statement.setString(9, object.getEmail());
			statement.setString(10, object.getCompany());
			statement.setString(11, object.getCountry());
			statement.setString(12, object.getCity());
			statement.setString(13, object.getStreet());
			statement.setString(14, object.getPostalCode());
			NullableHelper.setInt(statement, 15, object.getPhotoId());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Contact object) throws DataAccessException {
		try {
			java.sql.Date sqlDate = convertDateToSql(object.getDateOfBirth());
			statement.setString(1, object.getFirstName());
			statement.setString(2, object.getLastName());
			statement.setString(3, object.getMiddleName());
			statement.setDate(4, sqlDate);
			NullableHelper.setBool(statement, 5, object.getIsMale());
			statement.setString(6, object.getCitizenship());
			NullableHelper.setInt(statement, 7, object.getRelationshipStatusId());
			statement.setString(8, object.getWebsite());
			statement.setString(9, object.getEmail());
			statement.setString(10, object.getCompany());
			statement.setString(11, object.getCountry());
			statement.setString(12, object.getCity());
			statement.setString(13, object.getStreet());
			statement.setString(14, object.getPostalCode());
			NullableHelper.setInt(statement, 15, object.getPhotoId());
			NullableHelper.setInt(statement, 16, object.getId());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	protected java.sql.Date convertDateToSql(java.util.Date date) {
		if (date == null) {
			return null;
		}
		return new java.sql.Date(date.getTime());
	}

}
