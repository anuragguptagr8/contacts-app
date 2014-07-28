package com.itechart.training.tsvilik.contactsapp.web;

import javax.servlet.ServletRequest;

public class NotFoundResult extends ActionResult {
	
	public NotFoundResult(ServletRequest request) {
		super("/notfound.jsp", request);
	}
}
