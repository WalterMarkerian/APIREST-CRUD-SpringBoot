package com.springboot.gateway.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.gateway.dto.PublicacionDTO;
import com.springboot.gateway.entidades.Publicacion;
import com.springboot.gateway.excepciones.ResourceNotFoundException;
import com.springboot.gateway.repositorio.PublicacionRepositorio;
import com.springboot.gateway.translator.ConversorPublicacion;

@Service
public class PublicacionServicioImpl implements PublicacionServicio {

	@Autowired
	private PublicacionRepositorio publicacionRepositorio;

	@Autowired
	private ConversorPublicacion conversorPublicacion;

	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = conversorPublicacion.mapearEntidad(publicacionDTO);

		Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);

		PublicacionDTO publicacionRespuesta = conversorPublicacion.mapearDTO(nuevaPublicacion);
		return publicacionRespuesta;
	}

	@Override
	public List<PublicacionDTO> obtenerTodasLasPublicaciones() {
		List<Publicacion> publicaciones = publicacionRepositorio.findAll();
		return publicaciones.stream().map(publicacion -> conversorPublicacion.mapearDTO(publicacion))
				.collect(Collectors.toList());

	}

	@Override
	public PublicacionDTO obtenerPublicacionPorId(Long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		return conversorPublicacion.mapearDTO(publicacion);
	}

	@Override
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		
		Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);
		return conversorPublicacion.mapearDTO(publicacionActualizada);
	}

	@Override
	public void eliminarPublicacion(long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));		
		publicacionRepositorio.delete(publicacion);
	}

//	//Convierte entidad a DTO
//	private PublicacionDTO mapearDTO(Publicacion publicacion) {
//		PublicacionDTO publicacionDTO = new PublicacionDTO();
//		
//		publicacionDTO.setId(publicacion.getId());
//		publicacionDTO.setTitulo(publicacion.getTitulo());
//		publicacionDTO.setDescripcion(publicacion.getDescripcion());
//		publicacionDTO.setContenido(publicacion.getContenido());
//		
//		return publicacionDTO;
//	}
//	
//	//Convierte DTO a entidad
//	private Publicacion mapearEntidad(PublicacionDTO publicacionDTO) {
//		Publicacion publicacion = new Publicacion();
//		
//		publicacion.setTitulo(publicacionDTO.getTitulo());
//		publicacion.setDescripcion(publicacionDTO.getDescripcion());
//		publicacion.setContenido(publicacionDTO.getContenido());
//		
//		return publicacion;
//	}

}
