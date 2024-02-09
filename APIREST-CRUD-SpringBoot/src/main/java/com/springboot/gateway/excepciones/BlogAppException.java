package com.springboot.gateway.excepciones;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAppException extends RuntimeException {
	private static final Long serialVersionUID = 1L;
	
	private HttpStatus estado;
	private String mensaje;
	

	
	
	
}
