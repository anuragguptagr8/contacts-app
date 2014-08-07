package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.dao.AttachmentDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.BaseDbDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.NullableHelper;
import com.itechart.training.tsvilik.contactsapp.entities.Attachment;

public class MySqlAttachmentDao extends BaseDbDao<Attachment, Integer> implements AttachmentDao {

	protected String attachmentsTable = "`contacts-app-dev`.`attachments`";

	public MySqlAttachmentDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public String getSelectQuery() {
		return "SELECT `id`, `filename`, `real_filename`, `upload_date`, "
				+ "`comment`, `contact_id` FROM " + attachmentsTable;
	}

	@Override
	public String getInsertQuery() {
		return "INSERT INTO " + attachmentsTable + " (`filename`, "
				+ "`real_filename`, `upload_date`, "
				+ "`comment`, `contact_id`) VALUES (?, ?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE " + attachmentsTable + " SET `filename`=?, "
				+ "`comment`=?,  WHERE `id`=?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM " + attachmentsTable + " WHERE `id`=?;";
	}
	
	@Override
	protected String getCountQuery() {
		return "SELECT count(*) FROM " + attachmentsTable;
	}

	@Override
	protected List<Attachment> parseResultSet(ResultSet rs)
			throws DataAccessException {
		ArrayList<Attachment> result = new ArrayList<Attachment>();
		try {
			while (rs.next()) {
				Attachment attachment = new Attachment();
				attachment.setId(NullableHelper.getInt("id", rs));
				attachment.setFileName(rs.getString("filename"));
				attachment.setRealFileName(rs.getString("real_filename"));
				attachment.setUploadDate(rs.getDate("upload_date"));
				attachment.setComment(rs.getString("comment"));
				attachment.setContactId(NullableHelper.getInt("contact_id", rs));
				result.add(attachment);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Attachment object) throws DataAccessException {
		try {
			statement.setString(1, object.getFileName());
			statement.setString(2, object.getRealFileName());
			statement.setDate(3, convertDateToSql(object.getUploadDate()));
			statement.setString(4, object.getComment());
			NullableHelper.setInt(statement, 5, object.getContactId());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Attachment object) throws DataAccessException {
		try {
			statement.setString(1, object.getFileName());
			statement.setString(2, object.getComment());
			NullableHelper.setInt(statement, 3, object.getId());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public List<Attachment> getContactAttachments(int contactId) throws DataAccessException {
		List<Attachment> list;
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

	private java.sql.Date convertDateToSql(java.util.Date date) {
		if (date == null) {
			return null;
		}
		return new java.sql.Date(date.getTime());
	}
}
