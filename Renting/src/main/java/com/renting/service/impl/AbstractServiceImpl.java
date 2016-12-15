package com.renting.service.impl;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import com.renting.model.AbstractEntity;
import com.renting.repository.AbstractRepository;
import com.renting.service.AbstractService;


public abstract class AbstractServiceImpl<T extends AbstractEntity> implements AbstractService<T> {

	private AbstractRepository<T> genericRepository;

	public AbstractServiceImpl(AbstractRepository<T> abstractRepository) {
		this.genericRepository = abstractRepository;
	}

	@Override
	@Transactional
	public T save(T entity) {
		return afterSave(genericRepository.save(entity));
	} 

	@Override
	@Transactional
	public void update(T entity) {
		genericRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public T findById(int id) {

		return genericRepository.findById(id);
	}

	@Override
	@Transactional
	public void delete(int id) {
		genericRepository.delete(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Collection<T> getAll() {
		return genericRepository.findAll();
	}

	protected T afterSave(T entity) {
		return entity;
	}
}