package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import javax.sql.DataSource;

import com.itechart.training.tsvilik.contactsapp.dal.dao.BaseIdDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.CountryDao;

public class MySqlCountryDao extends BaseIdDao<String> implements CountryDao {

	public MySqlCountryDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	protected String getTableName() {
		return "`contacts-app-dev`.`countries`";
	}

}
