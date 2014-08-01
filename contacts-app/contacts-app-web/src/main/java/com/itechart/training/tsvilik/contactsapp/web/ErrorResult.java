package com.itechart.training.tsvilik.contactsapp.web;

import javax.servlet.http.HttpServletRequest;

public class ErrorResult extends ActionResult {
	
	public ErrorResult(HttpServletRequest request) {
		super("/error.jsp", request);
	}
}
