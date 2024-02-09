package com.springboot.gateway.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.gateway.dto.ComentarioDTO;
import com.springboot.gateway.servicio.ComentarioServicio;
import com.springboot.gateway.utilerias.AppConstantes;

@RestController
@RequestMapping("/api")
public class ComentarioControlador {

	@Autowired
	private ComentarioServicio comentarioServicio;
	
	@GetMapping("/publicaciones/{publicacionId}/comentarios")
	public List<ComentarioDTO> listarComentariosPorPublicacionId(@PathVariable(value = "publicacionId") Long publicacionId){
		return comentarioServicio.obtenerComentariosPorPublicacionId(publicacionId);
	}
	
	@GetMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<ComentarioDTO> obtenerComentarioPorId(@PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "id") Long comentarioId){
		ComentarioDTO comentarioDTO = comentarioServicio.obtenerComentarioPorId(publicacionId, comentarioId);
		return new ResponseEntity<>(comentarioDTO,HttpStatus.OK);
		
	}
	
	@PostMapping("/publicaciones/{publicacionId}/comentarios")
	public ResponseEntity<ComentarioDTO> guardarComentario(@PathVariable(value = "publicacionId") Long publicacionId, @RequestBody ComentarioDTO comentarioDTO){
		return new ResponseEntity<>(comentarioServicio.crearComentario(publicacionId, comentarioDTO),HttpStatus.CREATED);
	}
	
	@PutMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<ComentarioDTO> actualizarComentario(@PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "id") Long comentarioId,@RequestBody ComentarioDTO comentarioDTO){
		ComentarioDTO comentarioActualizado = comentarioServicio.actualizarComentario(publicacionId, comentarioId, comentarioDTO);
		return new ResponseEntity<>(comentarioActualizado,HttpStatus.OK);
	}
	
	@DeleteMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<String> eliminarComentario(@PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "id") Long comentarioId){
		comentarioServicio.eliminarComentario(publicacionId, comentarioId);
		return new ResponseEntity<>(AppConstantes.COMENTARIO_ELIMINADO, HttpStatus.OK);
	}
}
