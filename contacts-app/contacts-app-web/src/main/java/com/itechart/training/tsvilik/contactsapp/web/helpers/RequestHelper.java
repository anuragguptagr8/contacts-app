package com.itechart.training.tsvilik.contactsapp.web.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

public class RequestHelper {
	private static Logger logger = Logger.getLogger(RequestHelper.class);

	public static Map<String, String[]> extractRequestParameters(
			HttpServletRequest request) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(factory);
		Map<String, List<String>> fieldContainer = new HashMap<String, List<String>>();
		try {
			List<FileItem> requestItems = uploadHandler.parseRequest(request);
			for (FileItem item : requestItems) {
				String field = item.getFieldName();
				if (item.isFormField()) {
					String value = item.getString();
					fieldContainer.putIfAbsent(field, new ArrayList<String>());
					fieldContainer.get(field).add(value);
				} else {
					request.setAttribute(field, item);
				}
			}
		} catch (FileUploadException e) {
			logger.error("Error while processing multipart request.", e);
		}

		Map<String, String[]> result = new HashMap<String, String[]>();
		for (Entry<String, List<String>> entry : fieldContainer.entrySet()) {
			result.put(entry.getKey(), entry.getValue()
					.toArray(new String[] {}));
		}

		return result;
	}
}
