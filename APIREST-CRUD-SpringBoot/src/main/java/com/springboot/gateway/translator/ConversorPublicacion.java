package com.springboot.gateway.translator;

import org.springframework.stereotype.Component;

import com.springboot.gateway.dto.PublicacionDTO;
import com.springboot.gateway.entidades.Publicacion;

@Component
public class ConversorPublicacion {
	
	
	//Método para convertir Publicacion a PublicacionDTO
	public PublicacionDTO mapearDTO(Publicacion publicacion) {
		PublicacionDTO publicacionDTO = new PublicacionDTO();
		
		publicacionDTO.setId(publicacion.getId());
		publicacionDTO.setTitulo(publicacion.getTitulo());
		publicacionDTO.setDescripcion(publicacion.getDescripcion());
		publicacionDTO.setContenido(publicacion.getContenido());
		return publicacionDTO;	
	}
	
	//Método para convertir PublicacionDTO a Publicacion
	public Publicacion mapearEntidad(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = new Publicacion();
		
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		return publicacion;
		}
	
}
