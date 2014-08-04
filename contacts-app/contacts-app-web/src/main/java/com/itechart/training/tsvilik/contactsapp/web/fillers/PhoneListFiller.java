package com.itechart.training.tsvilik.contactsapp.web.fillers;

import java.util.List;
import java.util.Map;

import com.itechart.training.tsvilik.contactsapp.entities.PhoneNumber;

public class PhoneListFiller implements GenericBeanFiller<List<PhoneNumber>> {

	@Override
	public void fill(List<PhoneNumber> phoneList, Map<String, String[]> properties)
			throws PropertyFormatException {

		int contactId = convertToInt(properties.get("id")[0]);
		if (properties.get("phoneId") == null) {
			return;
		}
		int numberOfPhones = properties.get("phoneId").length;
		for (int i = 0; i < numberOfPhones; i++) {
			PhoneNumber phone = new PhoneNumber();
			phone.setId(convertToInt(properties.get("phoneId")[i]));
			phone.setCountryCode(convertToInt(properties.get("countryCode")[i]));
			phone.setOperatorCode(convertToInt(properties.get("operatorCode")[i]));
			phone.setNumber(convertToInt(properties.get("number")[i]));
			phone.setTypeId(convertToInt(properties.get("phoneTypeId")[i]));
			phone.setComment(properties.get("phoneComment")[i]);
			phone.setContactId(contactId);
			phoneList.add(phone);
		}
		
	}

	private Integer convertToInt(String numberString) {
		try {
			return Integer.parseInt(numberString);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
