package com.itechart.training.tsvilik.contactsapp.entities;

import java.util.Date;

public class SearchData {
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dateBefore;
	private Date dateAfter;
	private Boolean isMale;
	private String citizenship;
	private Integer relationshipStatusId;
	private String country;
	private String city;
	private String street;
	private String postalCode;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Boolean getIsMale() {
		return isMale;
	}

	public void setIsMale(Boolean isMale) {
		this.isMale = isMale;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public Integer getRelationshipStatusId() {
		return relationshipStatusId;
	}

	public void setRelationshipStatusId(Integer relationshipStatusId) {
		this.relationshipStatusId = relationshipStatusId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Date getDateBefore() {
		return dateBefore;
	}

	public void setDateBefore(Date dateBefore) {
		this.dateBefore = dateBefore;
	}

	public Date getDateAfter() {
		return dateAfter;
	}

	public void setDateAfter(Date dateAfter) {
		this.dateAfter = dateAfter;
	}

}
