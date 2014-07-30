package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.entities.PhoneNumber;

public class MySqlPhoneNumberDaoTest {

	private static MySqlPhoneNumberDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataSource ds = new TestDataSource();
		dao = new MySqlPhoneNumberDao(ds);
	}

	@Test
	public void test() {
		PhoneNumber number = new PhoneNumber();
		number.setComment("comment1");
		number.setContactId(2);
		number.setCountryCode(234);
		number.setNumber(4234242);
		number.setOperatorCode(33);
		number.setTypeId(1);
		
		PhoneNumber number2 = null;
		try {
			number = dao.insert(number);
			number2 = dao.getByKey(number.getId());
		} catch (DataAccessException e) {
			fail(e.getMessage());
		}
		assertTrue(number.getId().equals(number2.getId()));
		
		number.setComment("comment2");
		number.setContactId(2);
		number.setCountryCode(2334);
		number.setNumber(42342432);
		number.setOperatorCode(333);
		number.setTypeId(2);
		
		try {
			dao.update(number);
			number2 = dao.getByKey(number.getId());
		} catch (DataAccessException e) {
			fail(e.getMessage());
		}
		assertTrue(number.getComment().equals(number2.getComment()));
		
		try {
			dao.delete(number.getId());
			assertNull(dao.getByKey(number.getId()));
		} catch (DataAccessException e) {
			fail(e.getMessage());
		}
	}

}
