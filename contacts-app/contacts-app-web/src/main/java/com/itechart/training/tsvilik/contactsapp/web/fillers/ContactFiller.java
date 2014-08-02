package com.itechart.training.tsvilik.contactsapp.web.fillers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.itechart.training.tsvilik.contactsapp.entities.Contact;

public class ContactFiller implements GenericBeanFiller<Contact> {

	@Override
	public void fill(Contact contact, Map<String, String[]> properties)
			throws PropertyFormatException {

		try {
			contact.setId(Integer.parseInt(properties.get("id")[0]));
			contact.setRelationshipStatusId(Integer.parseInt(properties
					.get("relationshipStatusId")[0]));
			contact.setPohotoId(Integer.parseInt(properties.get("pohotoId")[0]));
		} catch (NumberFormatException e) {
			throw new PropertyFormatException("Failed to parse some id.", e);
		}
		contact.setFirstName(processString(properties.get("firstName")[0]));
		contact.setLastName(processString(properties.get("lastName")[0]));
		contact.setMiddleName(processString(properties.get("middleName")[0]));

		try {
			contact.setDateOfBirth(convertToDate(properties.get("dateOfBirth")[0]));
		} catch (ParseException e) {
			throw new PropertyFormatException("Failed to parse date of birth.", e);
		}

		contact.setIsMale(convertGenderToBool(properties.get("isMale")[0]));

		contact.setCitizenship(processString(properties.get("citizenship")[0]));
		contact.setWebsite(processString(properties.get("website")[0]));
		contact.setEmail(processString(properties.get("email")[0]));
		contact.setCompany(processString(properties.get("company")[0]));
		contact.setCountry(processString(properties.get("country")[0]));
		contact.setCity(processString(properties.get("city")[0]));
		contact.setStreet(processString(properties.get("street")[0]));
		contact.setPostalCode(processString(properties.get("postalCode")[0]));
	}

	private String processString(String string) {
		string = string.trim();
		return string.isEmpty() ? null : string;
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
