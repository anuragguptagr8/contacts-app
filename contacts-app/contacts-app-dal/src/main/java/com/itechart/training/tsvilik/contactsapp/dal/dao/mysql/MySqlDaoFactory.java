package com.itechart.training.tsvilik.contactsapp.dal.dao.mysql;

import javax.sql.DataSource;

import com.itechart.training.tsvilik.contactsapp.dal.dao.AttachmentDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.ContactDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.DaoFactory;
import com.itechart.training.tsvilik.contactsapp.dal.dao.PhoneNumberDao;
import com.itechart.training.tsvilik.contactsapp.dal.dao.PhotoDao;

public class MySqlDaoFactory implements DaoFactory {

	private DataSource dataSource;

	private ContactDao contactDao;
	private PhotoDao photoDao;
	private PhoneNumberDao phoneNumberDao;
	private AttachmentDao attachmentDao;

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

}
