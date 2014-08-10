package com.itechart.training.tsvilik.contactsapp.web.controllers;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.entities.Photo;
import com.itechart.training.tsvilik.contactsapp.web.ActionResult;
import com.itechart.training.tsvilik.contactsapp.web.BlManager;
import com.itechart.training.tsvilik.contactsapp.web.NotFoundResult;
import com.itechart.training.tsvilik.contactsapp.web.helpers.PhotoHelper;

public class PhotoController {

	private static Logger logger = Logger.getLogger(PhotoController.class);

	public ActionResult get(HttpServletRequest request) {
		Photo photo = null;
		try {
			int requestedPhotoId = Integer.parseInt(request
					.getParameter("id"));
			photo = BlManager.getPhotoManager().getByKey(
					requestedPhotoId);
		} catch (NumberFormatException | ModelException e) {
			logger.error("Failed to get photo.", e);
		}
		if (photo == null) {
			return new NotFoundResult(request);
		}
		File requestedFile = PhotoHelper.getPhotoFile(photo);
		if (requestedFile == null) {
			logger.error("photo " + photo.getId() + " " + photo.getFileName()
					+ " not found");
			return new NotFoundResult(request);
		}
		request.setAttribute("file", requestedFile);
		request.setAttribute("fileName", photo.getFileName());
		logger.info("requested photo file name is " + photo.getFileName());
		return new ActionResult("/download.jsp", request);
	}
}
