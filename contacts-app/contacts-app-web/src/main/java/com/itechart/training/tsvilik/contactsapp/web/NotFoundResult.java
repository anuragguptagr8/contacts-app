package com.itechart.training.tsvilik.contactsapp.web;

import javax.servlet.http.HttpServletRequest;

public class NotFoundResult extends ActionResult {
	
	public NotFoundResult(HttpServletRequest request) {
		super("/notfound.jsp", request);
	}
}
