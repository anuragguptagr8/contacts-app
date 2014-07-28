package com.itechart.training.tsvilik.contactsapp.web.controllers;

import javax.servlet.http.HttpServletRequest;

import com.itechart.training.tsvilik.contactsapp.web.ActionResult;

public class ContactController {
	public ActionResult get(HttpServletRequest request) {
		ActionResult ar = new ActionResult(request);
		ar.setReturnPage("/contact.jsp");
		return ar;
	}
}
