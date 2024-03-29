package com.itechart.training.tsvilik.contactsapp.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.entities.Attachment;
import com.itechart.training.tsvilik.contactsapp.entities.Contact;
import com.itechart.training.tsvilik.contactsapp.entities.PhoneNumber;
import com.itechart.training.tsvilik.contactsapp.entities.Photo;
import com.itechart.training.tsvilik.contactsapp.web.ActionResult;
import com.itechart.training.tsvilik.contactsapp.web.BlManager;
import com.itechart.training.tsvilik.contactsapp.web.ErrorResult;
import com.itechart.training.tsvilik.contactsapp.web.fillers.ContactFiller;
import com.itechart.training.tsvilik.contactsapp.web.fillers.GenericBeanFiller;
import com.itechart.training.tsvilik.contactsapp.web.fillers.PhoneListFiller;
import com.itechart.training.tsvilik.contactsapp.web.fillers.PropertyFormatException;
import com.itechart.training.tsvilik.contactsapp.web.helpers.AttachmentHelper;
import com.itechart.training.tsvilik.contactsapp.web.helpers.ContactHelper;
import com.itechart.training.tsvilik.contactsapp.web.helpers.PhotoHelper;
import com.itechart.training.tsvilik.contactsapp.web.helpers.RequestHelper;

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
			return getRetrievingContactFailedResult(request);
		}
		ActionResult result = new ActionResult("/contact.jsp", request);

		ContactHelper.loadPhoneNumbers(requestedContactId, request, result);
		ContactHelper.loadAttachments(requestedContactId, request, result);
		ContactHelper.loadAdditionalData(request, result);

		request.setAttribute("pageName", "Contact info");
		logger.info("showed contact's page");
		return result;
	}

	private ActionResult getRetrievingContactFailedResult(
			HttpServletRequest request) {
		ActionResult result = new ActionResult("/contacts", request);
		result.setRedirectNeeded(true);
		result.setMessage("The requested contact hasn't been found.");
		return result;
	}

	public ActionResult add(HttpServletRequest request) {
		ActionResult result = new ActionResult("/contact.jsp", request);
		ContactHelper.loadAdditionalData(request, result);
		request.setAttribute("pageName", "New contact");
		logger.info("showed add contact page");
		return result;
	}

	public ActionResult save(HttpServletRequest request) {
		ActionResult result = new ActionResult("/contact/add", request);
		result.setRedirectNeeded(true);
		Contact contact = new Contact();
		List<PhoneNumber> contactNumbers = new ArrayList<PhoneNumber>();
		GenericBeanFiller<Contact> contactFiller = new ContactFiller();
		GenericBeanFiller<List<PhoneNumber>> numberFiller = new PhoneListFiller();
		try {
			Map<String, String[]> requestParams = RequestHelper
					.extractRequestParameters(request);
			contactFiller.fill(contact, requestParams);
			if (contact.getId() != null) {
				result.setReturnPage("/contact?id=" + contact.getId());
			}
			Photo contactPhoto = PhotoHelper.getPhoto(request);
			if (contactPhoto != null) {
				contact.setPhotoId(contactPhoto.getId());
			}
			if (contact.getId() == null) {
				contact = BlManager.getContactManager().save(contact);
			} else {
				BlManager.getContactManager().update(contact);
			}
			requestParams
					.put("id", new String[] { contact.getId().toString() });
			numberFiller.fill(contactNumbers, requestParams);
			BlManager.getPhoneNumberManager().updateNumbers(contactNumbers);
			List<Attachment> attachments = AttachmentHelper.getAttachments(
					request, requestParams);
			BlManager.getAttachmentManager().updateAttachments(attachments);
			result.setReturnPage("/contact?id=" + contact.getId());
			result.setMessage("The contact has been saved successfully.");
			logger.info("The contact " + contact.getId()
					+ " has been successfully saved.");
		} catch (PropertyFormatException e) {
			logger.error("Failed to parse a property.", e);
			result.setMessage("Field format error: " + e.getMessage());
		} catch (ModelException e) {
			logger.error("Failed to save contact info.", e);
			result.setMessage("Failed to save contact info.");
		} finally {
			request.getSession().setAttribute("contact", contact);
			request.getSession().setAttribute("numbers", contactNumbers);
		}
		return result;
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
		logger.info("Selected contacts has been successfully removed.");
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
			request.setAttribute("nextPageUrl", "/contacts?page=");
			request.setAttribute("pageName", "Contacts List");
		} catch (ModelException e) {
			logger.error("Failed to list contacts", e);
			request.setAttribute("error-message", e.getMessage());
			return new ErrorResult(request);
		}

		logger.info("Showed contacts list");
		return new ActionResult("/contacts-list.jsp", request);
	}

}
