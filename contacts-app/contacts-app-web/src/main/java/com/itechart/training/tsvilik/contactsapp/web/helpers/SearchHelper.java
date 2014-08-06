package com.itechart.training.tsvilik.contactsapp.web.helpers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.web.BlManager;

public class SearchHelper {
	// private static Logger logger = Logger.getLogger(SearchHelper.class);

	public static int getRequestedPageNumber(HttpServletRequest request) {
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
		return pageNumber;
	}

	public static int getNumberOfPages(int numberOfResults)
			throws ModelException {
		int numberOfPages = numberOfResults / ContactHelper.CONTACTS_PER_PAGE;
		return numberOfResults % ContactHelper.CONTACTS_PER_PAGE == 0 ? numberOfPages
				: numberOfPages + 1;
	}

	public static void prepareSearchPage(HttpServletRequest request)
			throws ModelException {
		List<String> countries = BlManager.getCountryManager().getAllIds();
		request.setAttribute("countries", countries);
		List<Integer> relationshipStatuses = BlManager
				.getRelationshipStatusManager().getAllIds();
		request.setAttribute("relationships", relationshipStatuses);
	}

	public static String getNextPageUrl(HttpServletRequest request) {
		String query = request.getQueryString();
		if (query.indexOf("page") >= 0) {
			query = query.replaceAll("&page=\\d+", "");
		}
		query += "&page=";

		return "/contacts/search?" + query;
	}
}
