package com.eduarruiz.retosofka.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eduarruiz.retosofka.model.Role;

public interface RoleRepository extends MongoRepository<Role, Integer> {

}
