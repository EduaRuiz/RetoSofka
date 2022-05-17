package com.eduarruiz.retosofka.controller;

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

import com.eduarruiz.retosofka.model.Category;
import com.eduarruiz.retosofka.service.CategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {
		List<Category> categories = null;
		Map<String, Object> response = new HashMap<>();
		try {
			categories = categoryService.getAll();
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (categories != null) {
			return new ResponseEntity<>(categories, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		Category proveedor = null;
		Map<String, Object> response = new HashMap<>();
		try {
			proveedor = categoryService.get(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (proveedor == null) {
			response.put("error", "La categoría con el Id: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Category>(proveedor, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody Category proveedor) {
		//si se va a integrar en el futuro un metodo que valide y guarde categorias
		return null;
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody Category proveedor, @PathVariable Integer id) {
		//si se va a integrar en el futuro un metodo que valide y actualice categorias
		return null;
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Category proveedor = null;
		Map<String, Object> response = new HashMap<>();
		try {
			proveedor = categoryService.get(id);
			if (proveedor == null) {
				response.put("error", "El proveedor con el nit: ".concat(id.toString().concat(" no existe en la base!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			categoryService.delete(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la eliminación en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La categoría ha sido eliminado con éxito!");
		response.put("proveedor", proveedor);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
