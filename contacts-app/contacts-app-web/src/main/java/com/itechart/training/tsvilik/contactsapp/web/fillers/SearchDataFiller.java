package com.itechart.training.tsvilik.contactsapp.web.fillers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.itechart.training.tsvilik.contactsapp.entities.SearchData;

public class SearchDataFiller implements GenericBeanFiller<SearchData> {

	@Override
	public void fill(SearchData data, Map<String, String[]> properties)
			throws PropertyFormatException {

		Map<String, String> fields = processProperties(properties);

		data.setRelationshipStatusId(convertToInt(fields
				.get("relationshipStatusId")));

		data.setFirstName(fields.get("firstName"));
		data.setLastName(fields.get("lastName"));
		data.setMiddleName(fields.get("middleName"));

		try {
			data.setDateAfter(convertToDate(fields.get("dateAfter")));
			data.setDateBefore(convertToDate(fields.get("dateBefore")));
		} catch (ParseException e) {
			throw new PropertyFormatException(
					"Failed to parse date for search.", e);
		}

		data.setIsMale(convertGenderToBool(fields.get("gender")));

		data.setCitizenship(fields.get("citizenship"));
		data.setCountry(fields.get("country"));
		data.setCity(fields.get("city"));
		data.setStreet(fields.get("street"));
		data.setPostalCode(fields.get("postalCode"));
	}

	private Map<String, String> processProperties(
			Map<String, String[]> properties) {
		Map<String, String> processedProperties = new HashMap<String, String>();
		for (Entry<String, String[]> property : properties.entrySet()) {
			String value = property.getValue()[0];
			if (value != null) {
				value = value.trim();
				value = value.isEmpty() ? null : value;
			}
			processedProperties.put(property.getKey(), value);
		}
		return processedProperties;
	}

	private Integer convertToInt(String numberString) {
		try {
			return Integer.parseInt(numberString);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private Date convertToDate(String dateString) throws ParseException {
		if (dateString == null) {
			return null;
		}
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return df.parse(dateString.trim());
	}

	private Boolean convertGenderToBool(String gender) {
		if (gender == null) {
			return null;
		}
		gender = gender.trim();
		if (gender.equalsIgnoreCase("male")) {
			return true;
		} else if (gender.equalsIgnoreCase("female")) {
			return false;
		} else {
			return null;
		}
	}
}
