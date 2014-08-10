package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

import com.itechart.training.tsvilik.contactsapp.dal.dao.AttachmentDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.ContactDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.CountryDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.DaoFactory;
import com.itechart.training.tsvilik.contactsapp.dal.dao.PhoneNumberDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.PhoneTypeDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.PhotoDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.RelationshipStatusDao;

public class MySqlDaoFactory implements DaoFactory {
	private static Logger logger = Logger.getLogger(MySqlDaoFactory.class);
	
	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PASSWORD;

	static {
		String filename = "db.properties";
		ClassLoader classLoader = MySqlDaoFactory.class.getClassLoader();
		InputStream input = classLoader.getResourceAsStream(filename);
		Properties properties = new Properties();
		String dbUrl = null;
		String dbUser = null;
		String dbPassword = null;
		try {
			properties.load(input);
			dbUrl = properties.getProperty("url");
			dbUser = properties.getProperty("user");
			dbPassword = properties.getProperty("password");
		} catch (IOException | NumberFormatException e) {
			logger.error("Failed to get db properties.", e);
		} finally {
			DB_URL = dbUrl == null ? "jdbc:mysql://localhost:3306/contacts-app-dev" : dbUrl;
			DB_USER = dbUser == null ? "root" : dbUser;
			DB_PASSWORD = dbPassword == null ? "root" : dbPassword;
		}
	}
	
	private DataSource dataSource;

	private ContactDao contactDao;
	private PhotoDao photoDao;
	private PhoneNumberDao phoneNumberDao;
	private AttachmentDao attachmentDao;
	private CountryDao countryDao;
	private PhoneTypeDao phoneTypeDao;
	private RelationshipStatusDao relationshipStatusDao;

	public MySqlDaoFactory() {
		dataSource = new DataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USER);
		dataSource.setPassword(DB_PASSWORD);
		dataSource.setInitialSize(5);
		dataSource.setMaxActive(10);
		dataSource.setMaxIdle(5);
		dataSource.setMinIdle(2);
		dataSource.setTimeBetweenEvictionRunsMillis(5000);
		dataSource.setMinEvictableIdleTimeMillis(5000);
	}
	
	@Override
	public ContactDao getContactDao() {
		return contactDao == null ? new MySqlContactDao(dataSource)
				: contactDao;
	}

	@Override
	public PhotoDao getPhotoDao() {
		return photoDao == null ? new MySqlPhotoDao(dataSource) : photoDao;
	}

	@Override
	public PhoneNumberDao getPhoneNumberDao() {
		return phoneNumberDao == null ? new MySqlPhoneNumberDao(dataSource)
				: phoneNumberDao;
	}

	@Override
	public AttachmentDao getAttachmentDao() {
		return attachmentDao == null ? new MySqlAttachmentDao(dataSource)
				: attachmentDao;
	}

	@Override
	public CountryDao getCountryDao() {
		return countryDao == null ? new MySqlCountryDao(dataSource)
		: countryDao;
	}
	
	@Override
	public PhoneTypeDao getPhoneTypeDao() {
		return phoneTypeDao == null ? new MySqlPhoneTypeDao(dataSource)
		: phoneTypeDao;
	}
	
	@Override
	public RelationshipStatusDao getRelationshipStatusDao() {
		return relationshipStatusDao == null ? new MySqlRelationshipStatusDao(dataSource)
		: relationshipStatusDao;
	}
}
