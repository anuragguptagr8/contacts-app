package com.itechart.training.tsvilik.contactsapp.dal.transfer_objects;

import com.itechart.training.tsvilik.contactsapp.dal.Identifiable;

public class Attachment implements Identifiable<Integer> {
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
