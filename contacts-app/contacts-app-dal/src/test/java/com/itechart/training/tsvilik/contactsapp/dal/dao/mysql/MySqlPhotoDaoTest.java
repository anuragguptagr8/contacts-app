package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;

import com.itechart.training.tsvilik.contactsapp.dal.DataAccessException;
import com.itechart.training.tsvilik.contactsapp.dal.transfer_objects.Photo;

public class MySqlPhotoDaoTest {

	private static MySqlPhotoDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataSource ds = new TestDataSource();
		dao = new MySqlPhotoDao(ds);
	}

	@Test
	public void test() {
		Photo photo = new Photo();
		photo.setFileName("file1.jpg");
		
		Photo photo2 = null;
		try {
			photo = dao.insert(photo);
			photo2 = dao.getByKey(photo.getId());
		} catch (DataAccessException e) {
			fail(e.getMessage());
		}
		assertTrue(photo.getId().equals(photo2.getId()));
		
		photo.setFileName("file2.jpg");
		
		try {
			dao.update(photo);
			photo2 = dao.getByKey(photo.getId());
		} catch (DataAccessException e) {
			fail(e.getMessage());
		}
		assertTrue(photo.getFileName().equals(photo2.getFileName()));
		
		try {
			dao.delete(photo);
			assertNull(dao.getByKey(photo.getId()));
		} catch (DataAccessException e) {
			fail(e.getMessage());
		}
	}

}
