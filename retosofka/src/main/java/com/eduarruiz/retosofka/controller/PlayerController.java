package com.eduarruiz.retosofka.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eduarruiz.retosofka.model.Player;
import com.eduarruiz.retosofka.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
@CrossOrigin("*")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {
		List<Player> users = null;
		Map<String, Object> response = new HashMap<>();
		try {
			users = playerService.getAll();
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (users != null) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable String id) {
		Player user = null;
		Map<String, Object> response = new HashMap<>();
		try {
			user = playerService.get(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (user == null) {
			response.put("error",
					"El jugador con el email: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Player>(user, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody Player player) {
		Player user = null;
		Map<String, Object> response = new HashMap<>();
		if (player.validate() != null) {
			response.put("error", player.validate());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			player.setPassword(bCryptPasswordEncoder.encode(player.getPassword()));
			user = playerService.save(player);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar el insert en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El jugador ha sido creado con éxito!");
		response.put("player", user);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody Player player, @PathVariable String id) {
		Player updateUser = null;
		Map<String, Object> response = new HashMap<>();

		try {
			Player currentUser = playerService.get(id);
			if (currentUser == null) {
				response.put("error",
						"El jugador con el email: ".concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			if (!currentUser.getPassword().equals(player.getPassword())) {
				if (player.password() != null) {
					response.put("error", player.password());
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				}
				currentUser.setPassword(bCryptPasswordEncoder.encode(player.getPassword()));
			}
			if (player.validate() != null) {
				response.put("error", player.validate());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			currentUser.setEmailPlayer(player.getEmailPlayer());
			currentUser.setNamePlayer(player.getNamePlayer());
			currentUser.setUsername(player.getUsername());
			updateUser = playerService.save(currentUser);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la actualización en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El jugador ha sido actualizado con éxito!");
		response.put("player", updateUser);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		Player user = null;
		Map<String, Object> response = new HashMap<>();
		try {
			user = playerService.get(id);
			if (user == null) {
				response.put("error", "El jugador con el email: ".concat(id.toString().concat(" no existe en la base!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			playerService.delete(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la eliminación en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El jugador ha sido eliminado con éxito!");
		response.put("player", user);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
