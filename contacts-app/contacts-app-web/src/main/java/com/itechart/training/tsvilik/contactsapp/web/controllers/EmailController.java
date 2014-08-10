package com.itechart.training.tsvilik.contactsapp.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.entities.Contact;
import com.itechart.training.tsvilik.contactsapp.web.ActionResult;
import com.itechart.training.tsvilik.contactsapp.web.BlManager;
import com.itechart.training.tsvilik.contactsapp.web.helpers.ContactHelper;

public class EmailController {
	private static Logger logger = Logger.getLogger(EmailController.class);

	public ActionResult get(HttpServletRequest request) {
		List<Integer> idsToEmail = ContactHelper
				.getSelectedContactsIds(request);
		if (idsToEmail == null) {
			return getNoContactsResult(request);
		}
		logger.debug("Got " + idsToEmail.size() + " ids to email.");
		ActionResult actionResult = new ActionResult("/email.jsp", request);
		List<Contact> selectedContacts = new ArrayList<Contact>();
		String selectedEmails = "";
		for (Integer contactId : idsToEmail) {
			try {
				Contact contact = BlManager.getContactManager().getByKey(
						contactId);
				if (contact.getEmail() != null && !contact.getEmail().isEmpty()) {
					selectedContacts.add(contact);
					selectedEmails += contact.getEmail() + ", ";
				}
			} catch (ModelException e) {
				logger.error("Failed to get contact " + contactId
						+ " for email.");
			}
		}
		if (selectedContacts.size() == 0) {
			return getNoContactsResult(request);
		}
		request.setAttribute("contacts", selectedContacts);
		request.setAttribute("emails",
				selectedEmails.substring(0, selectedEmails.length() - 2));
		logger.info("Showed email page.");
		return actionResult;
	}

	private ActionResult getNoContactsResult(HttpServletRequest request) {
		ActionResult result = new ActionResult("/contacts", request);
		result.setRedirectNeeded(true);
		result.setMessage("No contacts selected or the contacts you've selected have no emails.");
		return result;
	}

	public ActionResult send(HttpServletRequest request) {
		List<Integer> idsToEmail = ContactHelper
				.getSelectedContactsIds(request);
		if (idsToEmail == null) {
			return getNoContactsResult(request);
		}
		List<Contact> selectedContacts = new ArrayList<Contact>();
		for (Integer contactId : idsToEmail) {
			try {
				selectedContacts.add(BlManager.getContactManager().getByKey(contactId));
			} catch (ModelException e) {
				logger.error("Failed to get contact " + contactId
						+ " for email.");
			}
		}

		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		ActionResult result = new ActionResult("/contacts", request);
		result.setRedirectNeeded(true);
		try {
			BlManager.getEmailManager().sendEmailTo(selectedContacts, subject, message);
			result.setMessage("The emails have been successfully sent.");
		} catch (ModelException e) {
			result.setMessage("Something has gone wrong while sending emails.");			
		}
		logger.info("The emails have been successfully sent.");
		return result;
	}
}
