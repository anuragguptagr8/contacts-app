package com.itechart.training.tsvilik.contactsapp.web;

import com.itechart.training.tsvilik.contactsapp.bl.AttachmentManager;
import com.itechart.training.tsvilik.contactsapp.bl.ContactManager;
import com.itechart.training.tsvilik.contactsapp.bl.CountryManager;
import com.itechart.training.tsvilik.contactsapp.bl.EmailManager;
import com.itechart.training.tsvilik.contactsapp.bl.PhoneNumberManager;
import com.itechart.training.tsvilik.contactsapp.bl.PhoneTypeManager;
import com.itechart.training.tsvilik.contactsapp.bl.PhotoManager;
import com.itechart.training.tsvilik.contactsapp.bl.RelationshipStatusManager;
import com.itechart.training.tsvilik.contactsapp.bl.simple.SimpleAttachmentManager;
import com.itechart.training.tsvilik.contactsapp.bl.simple.SimpleContactManager;
import com.itechart.training.tsvilik.contactsapp.bl.simple.SimpleCountryManager;
import com.itechart.training.tsvilik.contactsapp.bl.simple.SimpleEmailManager;
import com.itechart.training.tsvilik.contactsapp.bl.simple.SimplePhoneNumberManager;
import com.itechart.training.tsvilik.contactsapp.bl.simple.SimplePhoneTypeManager;
import com.itechart.training.tsvilik.contactsapp.bl.simple.SimplePhotoManager;
import com.itechart.training.tsvilik.contactsapp.bl.simple.SimpleRelationshipStatusManager;

public class BlManager {
	private static ContactManager contactManager;
	private static PhotoManager photoManager;
	private static PhoneNumberManager phoneNumberManager;
	private static AttachmentManager attachmentManager;
	private static CountryManager countryManager;
	private static PhoneTypeManager phoneTypeManager;
	private static RelationshipStatusManager relationshipStatusManager;
	private static EmailManager emailManager;

	public static ContactManager getContactManager() {
		return contactManager == null ? new SimpleContactManager()
				: contactManager;
	}

	public static PhotoManager getPhotoManager() {
		return photoManager == null ? new SimplePhotoManager() : photoManager;
	}

	public static PhoneNumberManager getPhoneNumberManager() {
		return phoneNumberManager == null ? new SimplePhoneNumberManager()
				: phoneNumberManager;
	}

	public static AttachmentManager getAttachmentManager() {
		return attachmentManager == null ? new SimpleAttachmentManager()
				: attachmentManager;
	}

	public static CountryManager getCountryManager() {
		return countryManager == null ? new SimpleCountryManager()
				: countryManager;
	}

	public static PhoneTypeManager getPhoneTypeManager() {
		return phoneTypeManager == null ? new SimplePhoneTypeManager()
				: phoneTypeManager;
	}

	public static RelationshipStatusManager getRelationshipStatusManager() {
		return relationshipStatusManager == null ? new SimpleRelationshipStatusManager()
				: relationshipStatusManager;
	}
	
	public static EmailManager getEmailManager() {
		return emailManager == null ? new SimpleEmailManager()
				: emailManager;
	}
}
