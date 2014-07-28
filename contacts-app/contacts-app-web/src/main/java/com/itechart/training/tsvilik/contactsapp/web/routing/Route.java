package com.itechart.training.tsvilik.contactsapp.web.routing;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "route")
public class Route {
	private String path;
	private String controller;
	private String action;

	@XmlElement
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path.trim();
	}

	@XmlElement
	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller.trim();
	}

	@XmlElement
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action.trim();
	}
}
