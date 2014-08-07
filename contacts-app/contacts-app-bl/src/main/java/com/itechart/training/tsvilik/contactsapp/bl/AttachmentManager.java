package com.itechart.training.tsvilik.contactsapp.bl;

import java.util.List;

import com.itechart.training.tsvilik.contactsapp.entities.Attachment;

public interface AttachmentManager extends EntityManager<Attachment, Integer> {

	List<Attachment> getContactAttachments(int contactId) throws ModelException;

	void updateAttachments(List<Attachment> attachments) throws ModelException;
}
