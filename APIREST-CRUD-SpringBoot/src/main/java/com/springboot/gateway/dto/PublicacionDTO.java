package com.springboot.gateway.dto;

import lombok.Data;

@Data
public class PublicacionDTO {

	private Long id;
	private String titulo;
	private String descripcion;
	private String contenido;
}
