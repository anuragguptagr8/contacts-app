package com.itechart.training.tsvilik.contactsapp.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.entities.Contact;
import com.itechart.training.tsvilik.contactsapp.entities.SearchData;
import com.itechart.training.tsvilik.contactsapp.web.ActionResult;
import com.itechart.training.tsvilik.contactsapp.web.BlManager;
import com.itechart.training.tsvilik.contactsapp.web.fillers.GenericBeanFiller;
import com.itechart.training.tsvilik.contactsapp.web.fillers.PropertyFormatException;
import com.itechart.training.tsvilik.contactsapp.web.fillers.SearchDataFiller;
import com.itechart.training.tsvilik.contactsapp.web.helpers.ContactHelper;
import com.itechart.training.tsvilik.contactsapp.web.helpers.SearchHelper;

public class SearchController {
	private static Logger logger = Logger.getLogger(ContactController.class);

	public ActionResult search(HttpServletRequest request) {
		if (request.getParameterMap().size() == 0) {
			return showSearchPage(request);
		}
		return showResults(request);
	}

	private ActionResult showResults(HttpServletRequest request) {
		ActionResult result = new ActionResult("/contacts-list.jsp", request);
		SearchData data = new SearchData();
		GenericBeanFiller<SearchData> searchDataFiller = new SearchDataFiller();
		try {
			searchDataFiller.fill(data, request.getParameterMap());
			int pageNumber = SearchHelper.getRequestedPageNumber(request);
			List<Contact> requestedContacts = BlManager.getContactManager()
					.search(data, ContactHelper.CONTACTS_PER_PAGE,
							pageNumber - 1);
			int totalResults = BlManager.getContactManager()
					.getSearchResultsCount(data);
			if (totalResults == 0) {
				result.setMessage("Nothing found.");
			} else {
				result.setMessage(totalResults
						+ " contact(s) match your search query.");
			}
			request.setAttribute("contacts", requestedContacts);
			request.setAttribute("page", pageNumber);
			request.setAttribute("totalpages",
					SearchHelper.getNumberOfPages(totalResults));
			request.setAttribute("nextPageUrl",
					SearchHelper.getNextPageUrl(request));
			request.setAttribute("pageName", "Search Results");
			logger.info("showed search results");
		} catch (PropertyFormatException e) {
			logger.error("Failed to parse a property.", e);
			result.setMessage("Field format error: " + e.getMessage());
		} catch (ModelException e) {
			logger.error("Search failed", e);
			result.setMessage("Search failed");
		} finally {
			request.getSession().setAttribute("contact", data);
		}
		return result;
	}

	private ActionResult showSearchPage(HttpServletRequest request) {
		ActionResult result = new ActionResult("/search.jsp", request);
		try {
			SearchHelper.prepareSearchPage(request);
		} catch (ModelException e) {
			logger.error("Failed to get necessary info for search page.");
			result.setMessage("Failed to get necessary info for search page.");
		}
		logger.info("showed search page");
		return result;
	}
}
