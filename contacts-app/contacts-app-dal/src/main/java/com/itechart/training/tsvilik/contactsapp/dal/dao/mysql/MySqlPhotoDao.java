package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.dao.BaseDbDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.PhotoDao;
import com.itechart.training.tsvilik.contactsapp.entities.Photo;

public class MySqlPhotoDao extends BaseDbDao<Photo, Integer> implements
		PhotoDao {
	protected String photosTable = "`contacts-app-dev`.`photos`";

	public MySqlPhotoDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public String getSelectQuery() {
		return "SELECT `id`, `filename` FROM " + photosTable;
	}

	@Override
	public String getInsertQuery() {
		return "INSERT INTO " + photosTable + " (`filename`) VALUES (?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE " + photosTable + " SET `filename`=? WHERE `id`=?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM " + photosTable + " WHERE `id`=?;";
	}

	@Override
	protected String getCountQuery() {
		return "SELECT count(*) FROM " + photosTable;
	}
	
	@Override
	protected List<Photo> parseResultSet(ResultSet rs)
			throws DataAccessException {
		ArrayList<Photo> result = new ArrayList<Photo>();
		try {
			while (rs.next()) {
				Photo photo = new Photo();
				photo.setId(rs.getInt("id"));
				photo.setFileName(rs.getString("filename"));
				result.add(photo);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Photo object) throws DataAccessException {
		try {
			statement.setString(1, object.getFileName());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Photo object) throws DataAccessException {
		try {
			statement.setString(1, object.getFileName());
			statement.setInt(2, object.getId());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

}
