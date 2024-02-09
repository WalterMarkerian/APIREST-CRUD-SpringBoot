package com.springboot.gateway.servicio;

import java.util.List;

import com.springboot.gateway.dto.ComentarioDTO;

public interface ComentarioServicio {
	
	public ComentarioDTO crearComentario(Long publicacionId, ComentarioDTO comentarioDTO);

	public List<ComentarioDTO> obtenerComentariosPorPublicacionId(Long publicacionId);
	
	public ComentarioDTO obtenerComentarioPorId(Long publicacionId, Long ComentarioId);
	
	public ComentarioDTO actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO solicitudDeComentario);
	
	public void eliminarComentario(Long publicacionId, Long comentarioId);
}
