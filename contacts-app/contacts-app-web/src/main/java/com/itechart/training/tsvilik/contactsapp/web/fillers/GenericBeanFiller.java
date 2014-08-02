package com.itechart.training.tsvilik.contactsapp.web.fillers;

import java.util.Map;

public interface GenericBeanFiller<T> {
	void fill(T bean, Map<String, String[]> properties)
			throws PropertyFormatException;
}
