package com.springboot.gateway.dto;

import lombok.Data;

@Data
public class ComentarioDTO {

	private Long id;
	private String nombre;
	private String email;
	private String cuerpo;
	
}
