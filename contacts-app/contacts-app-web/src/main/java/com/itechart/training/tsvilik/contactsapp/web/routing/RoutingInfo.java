package com.itechart.training.tsvilik.contactsapp.web.routing;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

@XmlRootElement(name = "routing-info")
public class RoutingInfo {

	private static Logger logger = Logger.getLogger(RoutingInfo.class);
	private static final String ROUTES_XML = "routes.xml";

	private String controllerPackage = "";
	private ArrayList<Route> routes;

	public RoutingInfo() {
		routes = new ArrayList<Route>();
	}

	@XmlElement(name = "controller-package")
	public String getControllerPackage() {
		return controllerPackage;
	}

	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage.trim();
	}

	@XmlElementWrapper(name = "routes")
	@XmlElement(name = "route")
	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = new ArrayList<Route>(routes);
	}

	public static RoutingInfo load() {
		try {
			ClassLoader classLoader = RoutingInfo.class.getClassLoader();
			InputStream inputStream = classLoader
					.getResourceAsStream(ROUTES_XML);
			JAXBContext context = JAXBContext.newInstance(RoutingInfo.class);
			Unmarshaller um = context.createUnmarshaller();
			RoutingInfo routingInfo = (RoutingInfo) um.unmarshal(inputStream);
			return routingInfo;
		} catch (JAXBException e) {
			logger.fatal("Failed to get routing info.", e);
			throw new RuntimeException(e);
		}
	}
}
