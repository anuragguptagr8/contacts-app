package com.itechart.training.tsvilik.contactsapp.web.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.entities.Contact;
import com.itechart.training.tsvilik.contactsapp.web.ActionResult;
import com.itechart.training.tsvilik.contactsapp.web.BlManager;
import com.itechart.training.tsvilik.contactsapp.web.ErrorResult;
import com.itechart.training.tsvilik.contactsapp.web.helpers.ContactHelper;

public class ContactController {
	private static Logger logger = Logger.getLogger(ContactController.class);

	public ActionResult get(HttpServletRequest request) {
		int requestedContactId;
		try {
			requestedContactId = Integer.parseInt(request.getParameter("id"));
			Contact requestedContact = BlManager.getContactManager().getByKey(
					requestedContactId);
			request.setAttribute("contact", requestedContact);
		} catch (NumberFormatException | ModelException e) {
			return getRetrievingContactFailed(request);
		}
		return new ActionResult("/contact.jsp", request);
	}

	private ActionResult getRetrievingContactFailed(HttpServletRequest request) {
		ActionResult result = new ActionResult("/contacts", request);
		result.setRedirectNeeded(true);
		result.setMessage("The requested contact hasn't been found.");
		return result;
	}

	public ActionResult add(HttpServletRequest request) {
		Contact contact = new Contact();

		try {
			BeanUtils.populate(contact, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.debug("populate error", e);
		}

		request.setAttribute("contact", contact);

		return new ActionResult("/error.jsp", request);
	}

	public ActionResult remove(HttpServletRequest request) {
		List<Integer> idsToRemove = ContactHelper
				.getSelectedContactsIds(request);
		logger.debug("Got " + idsToRemove.size() + " ids to remove.");
		ActionResult actionResult = new ActionResult("/contacts", request);
		actionResult.setRedirectNeeded(true);
		for (Integer contactId : idsToRemove) {
			try {
				BlManager.getContactManager().remove(contactId);
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

	public ActionResult list(HttpServletRequest request) {
		int pageNumber;
		try {
			pageNumber = ContactHelper.getRequestedPageNumber(request);
			List<Contact> requestedContacts = BlManager.getContactManager()
					.getBatch(ContactHelper.CONTACTS_PER_PAGE, pageNumber - 1);
			request.setAttribute("contacts", requestedContacts);
			request.setAttribute("page", pageNumber);
			request.setAttribute("totalpages", ContactHelper.getNumberOfPages());
		} catch (ModelException e) {
			logger.error("Failed to list contacts", e);
			request.setAttribute("error-message", e.getMessage());
			return new ErrorResult(request);
		}

		return new ActionResult("/contacts-list.jsp", request);
	}

}
