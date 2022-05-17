package com.eduarruiz.retosofka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "players")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
	
	@Id
	private String emailPlayer;
	private String namePlayer;
	private String password;
	private String username;
	private Long level;
	private Long score;
	private Integer idRol;
	
	//Se crean getters y setters en caso de mal funconamiento de lombok
	public String getEmailPlayer() {
		return emailPlayer;
	}

	public void setEmailPlayer(String emailPlayer) {
		this.emailPlayer = emailPlayer;
	}

	public String getNamePlayer() {
		return namePlayer;
	}

	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	
	//método para volver a validar datos con expresiones regulares
	private Boolean isValid(String regex, String value, Integer lenght) {
		if (value == null) {
			return false;
		}
		if (lenght != null && value.length() > lenght) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}

	public String validate() {
		if (!isValid("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-.]+\\.[a-zA-Z0-9]{2,6}$", this.emailPlayer, 60)) {
			return "Correo electrónico inválido, se permiten hata 60 caracteres, sin espacios";
		} else if (!isValid("^[a-zA-ZÀ-ÿ\\s]{3,40}$", this.namePlayer, 40)) {
			return "Nombre inválido, sólo se permiten entre 3 y 40 caracteres";
		}  else if (!isValid("^[a-zA-Z0-9_-]{4,40}$", this.username, 40)) {
			return "El nombre de usuario debe contener entre 4 y 40 caracteres sin espacios.";
		}
		return null;
	}

	public String password(){
		if (!isValid("^[a-zA-Z0-9!@#$%^&.*]{4,100}$", this.password, 100)) {
			return "La contraseña debe contener entre 4 y 100 caracteres sin espacios o caracteres especiales";
		}
		return null;
	}
}
