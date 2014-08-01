package com.itechart.training.tsvilik.contactsapp.web.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.web.BlManager;

public class ContactHelper {
	private static Logger logger = Logger.getLogger(ContactHelper.class);

	public static final int CONTACTS_PER_PAGE;

	static {
		String filename = "web.properties";
		ClassLoader classLoader = ContactHelper.class.getClassLoader();
		InputStream input = classLoader.getResourceAsStream(filename);
		Properties properties = new Properties();
		Integer contactsPerPage = null;
		try {
			properties.load(input);
			String contactsPerPageString = properties
					.getProperty("contacts_per_page");
			contactsPerPage = Integer.parseInt(contactsPerPageString);
		} catch (IOException | NumberFormatException e) {
			logger.error("Failed to get contactsPerPage property.", e);
		} finally {
			CONTACTS_PER_PAGE = contactsPerPage == null ? 10 : contactsPerPage;
		}
	}

	public static List<Integer> getSelectedContactsIds(
			HttpServletRequest request) {
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

	public static int getRequestedPageNumber(HttpServletRequest request)
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

	public static int getNumberOfPages() throws ModelException {
		int numberOfContacts = BlManager.getContactManager().getCount();
		int numberOfPages = numberOfContacts / CONTACTS_PER_PAGE;
		return numberOfContacts % CONTACTS_PER_PAGE == 0 ? numberOfPages
				: numberOfPages + 1;
	}

	public static void prepareContactPage(HttpServletRequest request)
			throws ModelException {
		List<String> countries = BlManager.getCountryManager().getAllIds();
		request.setAttribute("countries", countries);
		List<Integer> relationshipStatuses = BlManager
				.getRelationshipStatusManager().getAllIds();
		request.setAttribute("relationships", relationshipStatuses);
		List<Integer> phoneTypes = BlManager.getPhoneTypeManager().getAllIds();
		request.setAttribute("phone_types", phoneTypes);
	}
}
