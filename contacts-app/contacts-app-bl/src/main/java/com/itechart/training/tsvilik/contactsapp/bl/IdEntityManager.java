package com.itechart.training.tsvilik.contactsapp.bl;

import java.util.List;

public interface IdEntityManager<K> {
	List<K> getAllIds() throws ModelException;
}
