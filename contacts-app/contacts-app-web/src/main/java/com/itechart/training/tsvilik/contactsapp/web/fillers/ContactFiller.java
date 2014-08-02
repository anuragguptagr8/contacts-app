package com.itechart.training.tsvilik.contactsapp.web.fillers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.itechart.training.tsvilik.contactsapp.entities.Contact;

public class ContactFiller implements GenericBeanFiller<Contact> {

	@Override
	public void fill(Contact contact, Map<String, String[]> properties)
			throws PropertyFormatException {

		Map<String, String> fields = processProperties(properties);

		contact.setId(convertToInt(fields.get("id")));
		contact.setRelationshipStatusId(convertToInt(fields
				.get("relationshipStatusId")));
		contact.setPohotoId(convertToInt(fields.get("pohotoId")));
		
		contact.setFirstName(fields.get("firstName"));
		contact.setLastName(fields.get("lastName"));
		contact.setMiddleName(fields.get("middleName"));

		try {
			contact.setDateOfBirth(convertToDate(fields.get("dateOfBirth")));
		} catch (ParseException e) {
			throw new PropertyFormatException("Failed to parse date of birth.",
					e);
		}

		contact.setIsMale(convertGenderToBool(fields.get("isMale")));

		contact.setCitizenship(fields.get("citizenship"));
		contact.setWebsite(fields.get("website"));
		contact.setEmail(fields.get("email"));
		contact.setCompany(fields.get("company"));
		contact.setCountry(fields.get("country"));
		contact.setCity(fields.get("city"));
		contact.setStreet(fields.get("street"));
		contact.setPostalCode(fields.get("postalCode"));
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
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return df.parse(dateString.trim());
	}

	private Boolean convertGenderToBool(String gender) {
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
