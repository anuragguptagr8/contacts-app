package com.itechart.training.tsvilik.contactsapp.web.helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.entities.Attachment;

public class AttachmentHelper {
	private static Logger logger = Logger.getLogger(AttachmentHelper.class);

	private static final String SAVE_DIR;

	static {
		String filename = "web.properties";
		ClassLoader classLoader = AttachmentHelper.class.getClassLoader();
		InputStream input = classLoader.getResourceAsStream(filename);
		Properties properties = new Properties();
		String uploadsDir = null;
		try {
			properties.load(input);
			uploadsDir = properties.getProperty("uploads_dir");
		} catch (IOException | NumberFormatException e) {
			logger.error("Failed to get contactsPerPage property.", e);
		} finally {
			SAVE_DIR = uploadsDir == null ? "c:\\uploads" : uploadsDir;
		}
	}

	public static File getAttachedFile(Attachment attachment) {

		File requestedFile = new File(SAVE_DIR + File.separator
				+ attachment.getRealFileName());
		return requestedFile.exists() ? requestedFile : null;
	}

	public static List<Attachment> getAttachments(HttpServletRequest request,
			Map<String, String[]> requestParams) {

		List<Attachment> result = new ArrayList<Attachment>();

		int contactId = convertToInt(requestParams.get("id")[0]);
		if (requestParams.get("attachId") == null) {
			return result;
		}
		int numberOfFiles = requestParams.get("attachId").length;
		for (int i = 0; i < numberOfFiles; i++) {
			Attachment attach = new Attachment();
			attach.setId(convertToInt(requestParams.get("attachId")[i]));
			if (attach.getId() == null) {
				uploadFileFor(attach, requestParams.get("attachId")[i], request);
			}
			attach.setFileName(requestParams.get("attachName")[i]);
			attach.setComment(requestParams.get("attachComment")[i]);
			attach.setContactId(contactId);
			result.add(attach);
			logger.debug("File: " + attach.getFileName()
					+ " has been saved successfully.");
		}

		return result;
	}

	private static void uploadFileFor(Attachment attachment, String attachId,
			HttpServletRequest request) {
		logger.debug("load file for attachment with id " + attachId);
		try {
			File fileSaveDir = new File(SAVE_DIR);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			FileItem fileItem = (FileItem) request.getAttribute("attachFile"
					+ attachId);
			String uuidFileName = UUID.randomUUID().toString();
			File uploadedFile = new File(SAVE_DIR + File.separator
					+ uuidFileName);
			fileItem.write(uploadedFile);
			attachment.setRealFileName(uuidFileName);
			attachment.setUploadDate(Calendar.getInstance().getTime());
		} catch (Exception e) {
			logger.error("Error on saving uploaded file.", e);
		}
	}

	private static Integer convertToInt(String numberString) {
		try {
			return Integer.parseInt(numberString);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
