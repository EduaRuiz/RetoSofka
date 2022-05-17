package com.eduarruiz.retosofka.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.eduarruiz.retosofka.commons.GenericImpl;
import com.eduarruiz.retosofka.model.Role;
import com.eduarruiz.retosofka.repository.RoleRepository;
import com.eduarruiz.retosofka.service.RoleService;

@Service
public class RoleServiceImpl extends GenericImpl<Role, Integer> implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	protected CrudRepository<Role, Integer> getDao() {
		return roleRepository;
	}

	@Override
	public Boolean isRole(Integer idRole) {
		Optional<Role> list = roleRepository.findById(idRole);
		if (list.isPresent()) {
			return true;
		}
		return false;
	}
}
