package com.itechart.training.tsvilik.contactsapp.bl;

import java.util.List;

import com.itechart.training.tsvilik.contactsapp.entities.Contact;

public interface EmailManager {
	void sendEmailTo(List<Contact> recipients, String subject, String message) throws ModelException;
}
