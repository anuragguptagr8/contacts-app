package com.itechart.training.tsvilik.contactsapp.dal.dao;

public interface DaoFactory {
	ContactDao getContactDao();
	PhotoDao getPhotoDao();
	PhoneNumberDao getPhoneNumberDao();
	AttachmentDao getAttachmentDao();
}
