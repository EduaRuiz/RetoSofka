package com.eduarruiz.retosofka.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.eduarruiz.retosofka.commons.GenericService;
import com.eduarruiz.retosofka.model.Player;

public interface PlayerService extends GenericService<Player, String> {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	public Player findByUserName(String username);

}
