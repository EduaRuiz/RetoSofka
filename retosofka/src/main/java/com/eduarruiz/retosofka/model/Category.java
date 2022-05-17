package com.eduarruiz.retosofka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	@Id
	private Long idCategory;
	private String nombreCategory;
	
	
	//Se crean getters y setters en caso de mal funconamiento de lombok
	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public String getNombreCategory() {
		return nombreCategory;
	}

	public void setNombreCategory(String nombreCategory) {
		this.nombreCategory = nombreCategory;
	}
}
