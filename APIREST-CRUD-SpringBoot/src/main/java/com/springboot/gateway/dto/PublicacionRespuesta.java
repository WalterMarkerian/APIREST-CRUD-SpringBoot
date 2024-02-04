package com.springboot.gateway.dto;

import java.util.List;

import lombok.Data;

@Data
public class PublicacionRespuesta {

	private List<PublicacionDTO> contenido;
	private int numeroPagina;
	private int medidaPagina;
	private Long totalElementos;
	private int totalPaginas;
	private boolean ultima;
}
