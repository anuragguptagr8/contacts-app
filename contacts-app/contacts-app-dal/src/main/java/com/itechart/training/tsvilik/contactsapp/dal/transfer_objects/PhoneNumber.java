package com.itechart.training.tsvilik.contactsapp.dal.transfer_objects;

import com.itechart.training.tsvilik.contactsapp.dal.Identifiable;

public class PhoneNumber implements Identifiable<Integer> {
	private Integer id;
	private Integer countryCode;
	private Integer operatorCode;
	private Integer number;
	private Integer typeId;
	private String comment;
	private Integer contactId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(Integer operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
}
