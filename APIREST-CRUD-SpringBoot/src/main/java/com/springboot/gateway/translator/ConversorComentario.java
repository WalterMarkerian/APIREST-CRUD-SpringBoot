package com.springboot.gateway.translator;

import org.springframework.stereotype.Component;

import com.springboot.gateway.dto.ComentarioDTO;
import com.springboot.gateway.entidades.Comentario;

@Component
public class ConversorComentario {
	
	//Método para convertir Publicacion a PublicacionDTO
		public ComentarioDTO mapearDTO(Comentario comentario) {
			ComentarioDTO comentarioDTO = new ComentarioDTO();
			
			comentarioDTO.setId(comentario.getId());
			comentarioDTO.setNombre(comentario.getNombre());
			comentarioDTO.setEmail(comentario.getEmail());
			comentarioDTO.setCuerpo(comentario.getCuerpo());
			return comentarioDTO;	
		}
		
		//Método para convertir PublicacionDTO a Publicacion
		public Comentario mapearEntidad(ComentarioDTO comentarioDTO) {
			Comentario comentario = new Comentario();
			
			comentario.setNombre(comentarioDTO.getNombre());
			comentario.setEmail(comentarioDTO.getEmail());
			comentario.setCuerpo(comentarioDTO.getCuerpo());
			return comentario;
			}
		
	}