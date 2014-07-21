package com.itechart.training.tsvilik.contactsapp.dal.transfer_objects;

import com.itechart.training.tsvilik.contactsapp.dal.Identifiable;

public class Photo implements Identifiable<Integer>{
	private Integer id;
	private String fileName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
