package com.eduarruiz.retosofka.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.eduarruiz.retosofka.model.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
	
	@Query
	Player findByUsername(String username);

}
