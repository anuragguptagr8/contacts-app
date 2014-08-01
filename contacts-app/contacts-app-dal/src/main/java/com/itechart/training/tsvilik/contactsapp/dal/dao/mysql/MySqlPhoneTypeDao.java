package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import javax.sql.DataSource;

import com.itechart.training.tsvilik.contactsapp.dal.dao.BaseIdDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.PhoneTypeDao;

public class MySqlPhoneTypeDao extends BaseIdDao<Integer> implements PhoneTypeDao {

	public MySqlPhoneTypeDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	protected String getTableName() {
		return "`contacts-app-dev`.`phone_number_types`";
	}
	
}
