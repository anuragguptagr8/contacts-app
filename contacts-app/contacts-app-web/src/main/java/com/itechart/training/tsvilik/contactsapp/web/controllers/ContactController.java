package com.itechart.training.tsvilik.contactsapp.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.ContactManager;
import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.bl.simple.SimpleContactManager;
import com.itechart.training.tsvilik.contactsapp.entities.Contact;
import com.itechart.training.tsvilik.contactsapp.web.ActionResult;
import com.itechart.training.tsvilik.contactsapp.web.ErrorResult;

public class ContactController {
	private static Logger logger = Logger.getLogger(ContactController.class);

	private static final int CONTACTS_PER_PAGE = 10;

	private ContactManager contactManager;

	public ContactController() {
		contactManager = new SimpleContactManager();
	}

	public ActionResult get(HttpServletRequest request) {
		return new ActionResult("/contact.jsp", request);
	}

	public ActionResult list(HttpServletRequest request) {
		int pageNumber;
		try {
			pageNumber = getRequestedPageNumber(request);
			List<Contact> requestedContacts = contactManager.getBatch(
					CONTACTS_PER_PAGE, pageNumber - 1);
			logger.debug("Got " + requestedContacts.size() + " contacts");
			request.setAttribute("contacts", requestedContacts);
			request.setAttribute("page", pageNumber);
			request.setAttribute("totalpages", getNumberOfPages());
			logger.debug("total-pages: " + request.getAttribute("totalpages"));
		} catch (ModelException e) {
			logger.error("Failed to list contacts", e);
			request.setAttribute("error-message", e.getMessage());
			return new ErrorResult(request);
		}

		return new ActionResult("/contacts-list.jsp", request);
	}

	private int getRequestedPageNumber(HttpServletRequest request)
			throws ModelException {
		String pageNumberString = request.getParameter("page");
		int pageNumber = 1;
		if (pageNumberString == null) {
			return pageNumber;
		}
		try {
			pageNumber = Integer.parseInt(pageNumberString);
		} catch (NumberFormatException e) {
			return pageNumber;
		}
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		int numberOfPages = getNumberOfPages();
		if (pageNumber > numberOfPages) {
			pageNumber = numberOfPages;
		}
		return pageNumber;
	}

	private int getNumberOfPages() throws ModelException {
		int numberOfContacts = contactManager.getCount();
		logger.debug("total contacts: " + numberOfContacts);
		int numberOfPages = numberOfContacts / CONTACTS_PER_PAGE;
		logger.debug("total pages: " + numberOfPages);
		return numberOfContacts % CONTACTS_PER_PAGE == 0 ? numberOfPages
				: numberOfPages + 1;
	}
}
