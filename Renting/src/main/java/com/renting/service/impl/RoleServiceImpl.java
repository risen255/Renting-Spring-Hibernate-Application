package com.renting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renting.model.Role;
import com.renting.repository.RoleRepository;
import com.renting.service.RoleService;

@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role> implements RoleService {

	private RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository abstractRepository) {
		super(abstractRepository);
		this.roleRepository = abstractRepository;
	}
} 