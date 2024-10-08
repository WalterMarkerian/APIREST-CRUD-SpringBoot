package com.springboot.gateway.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
@Data
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String nombreDelRecurso;
	
	private String nombreDelCampo;
	
	private Long valorDelCampo;

	public ResourceNotFoundException(String nombreDelRecurso, String nombreDelCampo, long valorDelCampo) {
		super(String.format("%s no encontrado con : %s : '%s'",nombreDelRecurso,nombreDelCampo,valorDelCampo));
		this.nombreDelRecurso = nombreDelRecurso;
		this.nombreDelCampo = nombreDelCampo;
		this.valorDelCampo = valorDelCampo;
	}
	
	
}
