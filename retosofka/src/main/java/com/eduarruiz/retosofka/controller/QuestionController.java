package com.eduarruiz.retosofka.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduarruiz.retosofka.model.Player;
import com.eduarruiz.retosofka.model.Question;
import com.eduarruiz.retosofka.service.PlayerService;
import com.eduarruiz.retosofka.service.QuestionService;

@RestController
@RequestMapping("/questions")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private PlayerService playerService;

	//metodo pensado para tener un módulo gestor de preguntas en el futuro
	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {
		List<Question> questiones = null;
		Map<String, Object> response = new HashMap<>();
		try {
			questiones = questionService.getAll();
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (questiones != null) {
			return new ResponseEntity<>(questiones, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	//retorna la pregunta según el id elegido
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable String id) {
		Question question = null;
		Map<String, Object> response = new HashMap<>();
		try {
			question = questionService.get(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (question == null) {
			response.put("mensaje",
					"La pregunta con el Id: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Question>(question, HttpStatus.OK);
	}

	//Método más usaso, retorna un lista de preguntas según la categoría o nivel seleccionado
	@GetMapping(value = "/category/{id}")
	public ResponseEntity<?> category(@PathVariable Long id) {
		List<Question> questions = new ArrayList<Question>();
		Map<String, Object> response = new HashMap<>();
		try {
			questions = questionService.findByIdCategory(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (questions == null) {
			response.put("mensaje",
					"Las preguntas con la categoria: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	//segundo método más usado, con este método se validan las respuestas desde el backend dando mayor seguridad
	//este método también asigna el puntaje que tiene el jugador acumumulado y el nivel en el que se encuentra
	@PutMapping(value = "/validate/{id}")
	public ResponseEntity<?> validate(@RequestBody Question question, @PathVariable String id ) {
		Question currentQuestion = new Question();
		String correctAnswer = "";
		boolean isCorrect = false;
		Map<String, Object> response = new HashMap<>();
		try {
			currentQuestion = questionService.get(question.getQuestion());
			correctAnswer = currentQuestion.getOptions()[0];
			isCorrect = correctAnswer.equals(question.getAnswer());
			Player player = new Player();
			player = playerService.get(id);
			if(isCorrect){
				Long level = currentQuestion.getIdCategory();
				Long score = currentQuestion.getScore() + player.getScore();
				player.setScore(score);
				player.setLevel(level);
			}else{
				player.setLevel(1l);
				player.setScore(0l);
			}
			playerService.save(player);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(isCorrect, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody Question question) {
		//si se va a integrar en el futuro un metodo que valide y guarde preguntas
		return null;		
	}

	//por si se va a usar un módulo que importe preguntas desde un CSV
	@PostMapping(value = "/import")
	public ResponseEntity<?> importCsv(@RequestBody List<Question> questions) {
		Map<String, Object> response = new HashMap<>();
		List<String> mensajes = new ArrayList<String>();
		for (Question i : questions) {
			String x = save(i).getBody().toString();
			mensajes.add(x);
		}
		response.put("mensaje", mensajes);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody Question question, @PathVariable Long id) {
		//si se va a integrar en el futuro un metodo que valide y actualice preguntas
		return null;
	}

	//Elimina la pregunta elegida de la base de datos
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		Question question = null;
		Map<String, Object> response = new HashMap<>();
		try {
			question = questionService.get(id);
			if (question == null) {
				response.put("error", "La pregunta con el Id: ".concat(id.toString().concat(" no existe en la base!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			questionService.delete(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la eliminación en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La pregunta ha sido eliminado con éxito!");
		response.put("question", question);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
