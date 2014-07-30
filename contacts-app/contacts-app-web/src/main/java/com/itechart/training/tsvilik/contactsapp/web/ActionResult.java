package com.itechart.training.tsvilik.contactsapp.web;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class ActionResult {
	private String returnPage;
	private HttpServletRequest request;
	private boolean redirectNeeded;

	public ActionResult(String returnPage, HttpServletRequest request) {
		this.request = request;
		this.returnPage = returnPage;
		redirectNeeded = false;
	}
	
	public String getReturnPage() {
		return returnPage;
	}

	public void setReturnPage(String returnPage) {
		this.returnPage = returnPage;
	}

	public ServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public boolean isRedirectNeeded() {
		return redirectNeeded;
	}

	public void setRedirectNeeded(boolean redirectNeeded) {
		this.redirectNeeded = redirectNeeded;
	}

	public void setMessage(String message) {
		request.getSession().setAttribute("action_message", message);
	}
}
