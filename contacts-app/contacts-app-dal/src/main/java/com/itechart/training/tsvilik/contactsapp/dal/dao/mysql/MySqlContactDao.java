package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.dao.BaseDbDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.ContactDao;
import com.itechart.training.tsvilik.contactsapp.dal.transfer_objects.Contact;

public class MySqlContactDao extends BaseDbDao<Contact, Integer> implements ContactDao{

	protected String contactsTable = "`contacts-app-dev`.`contacts`";

	public MySqlContactDao(Connection connection) {
		super(connection);
	}

	@Override
	public String getSelectQuery() {
		return "SELECT `id`, `first_name`, `last_name`, `middle_name`, "
				+ "`date_of_birth`,	`is_male`, `citizenship`, "
				+ "`relationship_status_id`, `web_site`, `email`, `company`, "
				+ "`country_id`, `city`, `street_address`, `zip`, `photo_id` "
				+ "FROM " + contactsTable;
	}

	@Override
	public String getInsertQuery() {
		return "INSERT INTO " + contactsTable + " (`first_name`, `last_name`, "
				+ "`middle_name`, `date_of_birth`, `is_male`, `citizenship`, "
				+ "`relationship_status_id`, `web_site`, `email`, `company`, "
				+ "`country_id`, `city`, `street_address`, `zip`, `photo_id`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE " + contactsTable
				+ " SET `first_name`=?, `last_name`=?,"
				+ " `middle_name`=?, `date_of_birth`=?, `is_male`=?,"
				+ " `citizenship`=?, `relationship_status_id`=?, `web_site`=?,"
				+ " `email`=?, `company`=?, `country_id`=?, `city`=?,"
				+ " `street_address`=?, `zip`=?, `photo_id`=? WHERE `id`=?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM " + contactsTable + " WHERE `id`=?;";
	}

	@Override
	protected List<Contact> parseResultSet(ResultSet rs)
			throws DataAccessException {
		ArrayList<Contact> result = new ArrayList<Contact>();
        try {
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
                contact.setMiddleName(rs.getString("middle_name"));
                contact.setDateOfBirth(rs.getDate("date_of_birth"));
                contact.setIsMale(rs.getBoolean("is_male"));
                contact.setCitizenship(rs.getString("citizenship"));
                contact.setRelationshipStatusId(rs.getInt("relationship_status_id"));
                contact.setWebsite(rs.getString("web_site"));
                contact.setEmail(rs.getString("email"));
                contact.setCompany(rs.getString("company"));
                contact.setCountry(rs.getString("country_id"));
                contact.setCity(rs.getString("city"));
                contact.setStreet(rs.getString("street_address"));
                contact.setPostalCode(rs.getString("zip"));
                contact.setPohotoId(rs.getInt("photo_id"));
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
            statement.setBoolean(5, object.getIsMale());
            statement.setString(6, object.getCitizenship());
            statement.setInt(7, object.getRelationshipStatusId());
            statement.setString(8, object.getWebsite());
            statement.setString(9, object.getEmail());
            statement.setString(10, object.getCompany());
            statement.setString(11, object.getCountry());
            statement.setString(12, object.getCity());
            statement.setString(13, object.getStreet());
            statement.setString(14, object.getPostalCode());
            statement.setInt(15, object.getPohotoId());
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
            statement.setBoolean(5, object.getIsMale());
            statement.setString(6, object.getCitizenship());
            statement.setInt(7, object.getRelationshipStatusId());
            statement.setString(8, object.getWebsite());
            statement.setString(9, object.getEmail());
            statement.setString(10, object.getCompany());
            statement.setString(11, object.getCountry());
            statement.setString(12, object.getCity());
            statement.setString(13, object.getStreet());
            statement.setString(14, object.getPostalCode());
            statement.setInt(15, object.getPohotoId());
            statement.setInt(16, object.getId());
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
