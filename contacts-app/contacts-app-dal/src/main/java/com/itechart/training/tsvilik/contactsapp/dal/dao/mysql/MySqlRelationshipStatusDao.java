package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import javax.sql.DataSource;

import com.itechart.training.tsvilik.contactsapp.dal.dao.BaseIdDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.RelationshipStatusDao;

public class MySqlRelationshipStatusDao extends BaseIdDao<Integer> implements RelationshipStatusDao {

	public MySqlRelationshipStatusDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	protected String getTableName() {
		return "`contacts-app-dev`.`relationship_statuses`";
	}
	
}
