package com.itechart.training.tsvilik.contactsapp.web.controllers;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.bl.ModelException;
import com.itechart.training.tsvilik.contactsapp.entities.Attachment;
import com.itechart.training.tsvilik.contactsapp.web.ActionResult;
import com.itechart.training.tsvilik.contactsapp.web.BlManager;
import com.itechart.training.tsvilik.contactsapp.web.NotFoundResult;
import com.itechart.training.tsvilik.contactsapp.web.helpers.AttachmentHelper;

public class AttachmentController {

	private static Logger logger = Logger.getLogger(AttachmentController.class);

	public ActionResult get(HttpServletRequest request) {
		Attachment attachment = null;
		try {
			int requestedAttachmentId = Integer.parseInt(request
					.getParameter("id"));
			attachment = BlManager.getAttachmentManager().getByKey(
					requestedAttachmentId);
		} catch (NumberFormatException | ModelException e) {
			logger.error("Failed to get attachment.", e);
		}
		if (attachment == null) {
			return new NotFoundResult(request);
		}
		File requestedFile = AttachmentHelper.getAttachedFile(attachment);
		if (requestedFile == null) {
			return new NotFoundResult(request);
		}
		request.setAttribute("file", requestedFile);
		request.setAttribute("fileName", attachment.getFileName());
		return new ActionResult("/download.jsp", request);
	}
}
