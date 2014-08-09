package com.itechart.training.tsvilik.contactsapp.bl.jobs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.itechart.training.tsvilik.contactsapp.bl.ContactManager;
import com.itechart.training.tsvilik.contactsapp.bl.EmailManager;
import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.bl.simple.SimpleContactManager;
import com.itechart.training.tsvilik.contactsapp.bl.simple.SimpleEmailManager;
import com.itechart.training.tsvilik.contactsapp.entities.Contact;

public class EmailBirthdayJob implements Job {

	private static Logger logger = Logger.getLogger(EmailBirthdayJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		EmailManager emailManager = new SimpleEmailManager();
		ContactManager contactManager = new SimpleContactManager();
		List<Contact> admin = new ArrayList<Contact>();
		admin.add(new Contact() {
			public String getEmail() {
				return "contactsapp2014@gmail.com";
			};
		});
		
		Date today = Calendar.getInstance().getTime();
		try {
			List<Contact> contacts = contactManager.getByBirthday(today);
			if (contacts.size() == 0) {
				logger.info("Nobody has a birthday today.");
				return;
			}
			String message = createNotificationMessage(contacts);
			emailManager.sendEmailTo(admin, "Birthday notification", message);
			logger.info(contacts.size() + " have birthday today");
		} catch (ModelException e) {
			logger.error("EmailBirthdayJob failed", e);
			throw new JobExecutionException(e);
		}

	}

	private String createNotificationMessage(List<Contact> contacts) {
		StringBuilder message = new StringBuilder();
		message.append("The following contacts have birthday today ");
		message.append(Calendar.getInstance().getTime() + ":\n");
		for (Contact contact : contacts) {
			message.append("id " + contact.getId() + " ");
			message.append(contact.getFirstName() + " ");
			message.append(contact.getLastName() + "\n");
		}

		return message.toString();
	}
}
