package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

class TestDataSource implements DataSource {
	
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
			System.out.println("Failed to get test db properties.");
		} finally {
			DB_URL = dbUrl == null ? "jdbc:mysql://localhost:3306/contacts-app-dev" : dbUrl;
			DB_USER = dbUser == null ? "root" : dbUser;
			DB_PASSWORD = dbPassword == null ? "root" : dbPassword;
		}
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = DB_URL;
		String user = DB_USER;
		String password = DB_PASSWORD;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = DriverManager
				.getConnection(url, user, password);
		return connection;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
