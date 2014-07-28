package com.itechart.training.tsvilik.contactsapp.web;

import javax.servlet.ServletRequest;

public class ErrorResult extends ActionResult {
	
	public ErrorResult(ServletRequest request) {
		super("/error.jsp", request);
	}
}
