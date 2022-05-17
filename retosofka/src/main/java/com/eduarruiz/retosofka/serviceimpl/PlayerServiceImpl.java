package com.eduarruiz.retosofka.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduarruiz.retosofka.commons.GenericImpl;
import com.eduarruiz.retosofka.model.Player;
import com.eduarruiz.retosofka.repository.PlayerRepository;
import com.eduarruiz.retosofka.service.RoleService;
import com.eduarruiz.retosofka.service.PlayerService;

@Service
public class PlayerServiceImpl extends GenericImpl<Player, String> implements PlayerService, UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	protected CrudRepository<Player, String> getDao() {
		return playerRepository;
	}

	@Autowired
	private RoleService roleService;

	//valida si el jugador existe y qué rol tiene
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Player player = playerRepository.findByUsername(username);
		if (player == null) {
			logger.error("No existe el jugador: " + username);
			throw new UsernameNotFoundException("No existe el jugador: " + username);
		}
		List<String> roles = new ArrayList<String>();		
		Integer idRol = player.getIdRol();
		roles.add(roleService.get(idRol).getName());
		List<GrantedAuthority> authorities = roles
				.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		return new User(player.getUsername(), player.getPassword(), authorities);
	}

	@Override
	public Player findByUserName(String username) {
		Player player = playerRepository.findByUsername(username);
		return player;
	}
}
