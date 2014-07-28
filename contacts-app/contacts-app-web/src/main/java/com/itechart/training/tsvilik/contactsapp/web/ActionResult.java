package com.itechart.training.tsvilik.contactsapp.web;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletRequest;

public class ActionResult {
	private String returnPage;
	private ServletRequest request;

	public ActionResult(ServletRequest request) {
		this.request = request;
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

	public void setRequest(ServletRequest request) {
		this.request = request;
	}

}
