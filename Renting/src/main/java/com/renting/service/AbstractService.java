package com.renting.service;

import java.util.Collection;

import com.renting.model.AbstractEntity;

public interface AbstractService<T extends AbstractEntity> {

	T save(T entity);
	
	T findById(int id); 
	
	void update(T entity);

	void delete(int id);

	Collection<T> getAll();
}
