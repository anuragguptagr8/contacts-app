package com.itechart.training.tsvilik.contactsapp.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class ApplicationServletContextListener implements
		ServletContextListener {
	public void contextInitialized(ServletContextEvent event) {
		ServletContext ctx = event.getServletContext();

		String prefix = ctx.getRealPath("/");
//		System.setProperty("appRootPath", prefix);
		String file = "WEB-INF" + System.getProperty("file.separator")
				+ "classes" + System.getProperty("file.separator")
				+ "log4j.properties";

		PropertyConfigurator.configure(prefix + file);
		System.out.println("Log4J Logging started for application: " + prefix
				+ file);

	}

	public void contextDestroyed(ServletContextEvent event) {

	}

}