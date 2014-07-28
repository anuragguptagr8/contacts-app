package com.itechart.training.tsvilik.contactsapp.web.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.web.ActionDispatcher;
import com.itechart.training.tsvilik.contactsapp.web.ActionResult;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = -1898517924450742571L;
	private static Logger logger = Logger.getLogger(ActionDispatcher.class);

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ActionResult result = ActionDispatcher.dispatch(request);
		// logger.debug("Got result: " + result.getReturnPage());
		getServletContext().getRequestDispatcher(result.getReturnPage())
				.forward(result.getRequest(), response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
