package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.dao.AttachmentDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.BaseDbDao;
import com.itechart.training.tsvilik.contactsapp.entities.Attachment;

public class MySqlAttachmentDao extends BaseDbDao<Attachment, Integer> implements AttachmentDao {

	public MySqlAttachmentDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public String getSelectQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInsertQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeleteQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCountQuery() {
		return null;
	}
	
	@Override
	protected List<Attachment> parseResultSet(ResultSet rs)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Attachment object) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Attachment object) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

}
