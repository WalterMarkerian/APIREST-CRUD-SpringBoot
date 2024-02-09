package com.springboot.gateway.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.gateway.dto.ComentarioDTO;
import com.springboot.gateway.entidades.Comentario;
import com.springboot.gateway.entidades.Publicacion;
import com.springboot.gateway.excepciones.BlogAppException;
import com.springboot.gateway.excepciones.ResourceNotFoundException;
import com.springboot.gateway.repositorio.ComentarioRepositorio;
import com.springboot.gateway.repositorio.PublicacionRepositorio;
import com.springboot.gateway.translator.ConversorComentario;
import com.springboot.gateway.utilerias.AppConstantes;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{
	
	@Autowired
	private ComentarioRepositorio comentarioRepositorio;
	
	@Autowired
	private PublicacionRepositorio publicacionRepositorio;
	
	@Autowired
	private ConversorComentario conversorComentario;

	
	@Override
	public ComentarioDTO crearComentario(Long publicacionId, ComentarioDTO comentarioDTO) {
		Comentario comentario = conversorComentario.mapearEntidad(comentarioDTO);
		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		comentario.setPublicacion(publicacion);
		Comentario nuevComentario = comentarioRepositorio.save(comentario);
		return conversorComentario.mapearDTO(nuevComentario);
	}

	@Override
	public List<ComentarioDTO> obtenerComentariosPorPublicacionId(Long publicacionId) {
		List<Comentario> comentarios = comentarioRepositorio.findByPublicacionId(publicacionId);
		return comentarios.stream().map(comentario -> conversorComentario.mapearDTO(comentario)).collect(Collectors.toList());
	}

	@Override
	public ComentarioDTO obtenerComentarioPorId(Long publicacionId, Long comentarioId) {
		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
		
		Comentario comentario = comentarioRepositorio.findById(comentarioId)
				.orElseThrow(()-> new ResourceNotFoundException("Comentario", "id", comentarioId));
		
		if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, AppConstantes.COMENTARIO_NO_PERTENECE);
		}
		return conversorComentario.mapearDTO(comentario);
	}

	@Override
	public ComentarioDTO actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO solicitudDeComentario) {
		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
		
		Comentario comentario = comentarioRepositorio.findById(comentarioId)
				.orElseThrow(()-> new ResourceNotFoundException("Comentario", "id", comentarioId));
		
		if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, AppConstantes.COMENTARIO_NO_PERTENECE);
		}
		comentario.setNombre(solicitudDeComentario.getNombre());
		comentario.setEmail(solicitudDeComentario.getEmail());
		comentario.setCuerpo(solicitudDeComentario.getCuerpo());
		
		Comentario comentarioActualizado = comentarioRepositorio.save(comentario);
		return conversorComentario.mapearDTO(comentarioActualizado);
	}

	@Override
	public void eliminarComentario(Long publicacionId, Long comentarioId) {
		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
		
		Comentario comentario = comentarioRepositorio.findById(comentarioId)
				.orElseThrow(()-> new ResourceNotFoundException("Comentario", "id", comentarioId));
		
		if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, AppConstantes.COMENTARIO_NO_PERTENECE);
		}
		comentarioRepositorio.delete(comentario);	

	}

	

	
}
