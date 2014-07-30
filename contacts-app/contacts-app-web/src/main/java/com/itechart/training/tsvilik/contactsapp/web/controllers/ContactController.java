package com.itechart.training.tsvilik.contactsapp.web.controllers;

import java.util.ArrayList;
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
		request.setAttribute("action", "get");
		return new ActionResult("/contact.jsp", request);
	}

	public ActionResult add(HttpServletRequest request) {
		request.setAttribute("action", "add");
		return new ActionResult("/contact.jsp", request);
	}

	public ActionResult remove(HttpServletRequest request) {
		List<Integer> idsToRemove = getSelectedContactsIds(request);
		logger.debug("Got " + idsToRemove.size() + " ids to remove.");
		ActionResult actionResult = new ActionResult("/contacts",
				request);
		actionResult.setRedirectNeeded(true);
		for (Integer contactId : idsToRemove) {
			try {
				contactManager.remove(contactId);
				logger.debug("Removed contact " + contactId);
			} catch (ModelException e) {
				logger.error("Failed to remove contact with id " + contactId, e);
				actionResult.setMessage("Failed to remove contact with id "
						+ contactId
						+ ". The removal procedure has been stopped.");
				return actionResult;
			}
		}
		actionResult
				.setMessage("Selected contacts has been successfully removed.");
		return actionResult;
	}

	private List<Integer> getSelectedContactsIds(HttpServletRequest request) {
		String[] selectedIdStrings = request
				.getParameterValues("selected_contacts");
		List<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i < selectedIdStrings.length; i++) {
			try {
				idList.add(Integer.parseInt(selectedIdStrings[i]));
			} catch (NumberFormatException e) {
				continue;
			}
		}
		return idList;
	}

	public ActionResult list(HttpServletRequest request) {
		int pageNumber;
		try {
			pageNumber = getRequestedPageNumber(request);
			List<Contact> requestedContacts = contactManager.getBatch(
					CONTACTS_PER_PAGE, pageNumber - 1);
			request.setAttribute("contacts", requestedContacts);
			request.setAttribute("page", pageNumber);
			request.setAttribute("totalpages", getNumberOfPages());
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
		int numberOfPages = numberOfContacts / CONTACTS_PER_PAGE;
		return numberOfContacts % CONTACTS_PER_PAGE == 0 ? numberOfPages
				: numberOfPages + 1;
	}
}
