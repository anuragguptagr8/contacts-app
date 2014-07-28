package com.itechart.training.tsvilik.contactsapp.web.controllers;

import javax.servlet.http.HttpServletRequest;

import com.itechart.training.tsvilik.contactsapp.web.ActionResult;

public class HomeController {
	public ActionResult index(HttpServletRequest request) {
		ActionResult ar = new ActionResult(request);
		ar.setReturnPage("/index.jsp");
		return ar;
	}
}
