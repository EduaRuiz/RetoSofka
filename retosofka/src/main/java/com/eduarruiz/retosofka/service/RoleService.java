package com.eduarruiz.retosofka.service;

import com.eduarruiz.retosofka.commons.GenericService;
import com.eduarruiz.retosofka.model.Role;

public interface RoleService extends GenericService<Role, Integer> {

  Boolean isRole(Integer idRole);

}
