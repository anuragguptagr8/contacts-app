package com.itechart.training.tsvilik.contactsapp.web.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itechart.training.tsvilik.contactsapp.web.ActionDispatcher;
import com.itechart.training.tsvilik.contactsapp.web.ActionResult;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = -1898517924450742571L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ActionResult result = ActionDispatcher.dispatch(request);
		
		if (result.isRedirectNeeded()) {
			response.sendRedirect(request.getContextPath() + result.getReturnPage());
		} else {
			getServletContext().getRequestDispatcher(result.getReturnPage())
					.forward(result.getRequest(), response);
			request.getSession().removeAttribute("action_message");
			request.getSession().removeAttribute("contact");
		}
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
