package com.itechart.training.tsvilik.contactsapp.web.routing;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoutingInfoTest {

	@Test
	public void testLoading() {
		RoutingInfo ri = RoutingInfo.load();
		assertEquals(ri.getControllerPackage(),
				"com.itechart.training.tsvilik.contactsapp.web.controllers");
		
		Route r = ri.getRoutes().get(1);
		assertEquals(r.getPath(), "/contacts");
	}

}
