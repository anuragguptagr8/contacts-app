package com.itechart.training.tsvilik.contactsapp.entities;

import java.util.Date;

public class Contact implements Identifiable<Integer>{
	private Integer id;
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dateOfBirth;
	private Boolean isMale;
	private String citizenship;
	private Integer relationshipStatusId;
	private String website;
	private String email;
	private String company;
	private String country;
	private String city;
	private String street;
	private String postalCode;
	private Integer pohotoId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public Integer getPohotoId() {
		return pohotoId;
	}

	public void setPohotoId(Integer pohotoId) {
		this.pohotoId = pohotoId;
	}

}
