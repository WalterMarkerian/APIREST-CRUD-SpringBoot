package com.springboot.gateway.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.gateway.dto.PublicacionDTO;
import com.springboot.gateway.dto.PublicacionRespuesta;
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
	public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
		
		Page<Publicacion> publicaciones = publicacionRepositorio.findAll(pageable);
				
		List<Publicacion> listaDePublicaciones = publicaciones.getContent();
		List<PublicacionDTO> contenido = listaDePublicaciones.stream().map(publicacion -> conversorPublicacion.mapearDTO(publicacion))
				.collect(Collectors.toList());

		PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
		publicacionRespuesta.setContenido(contenido);
		publicacionRespuesta.setNumeroPagina(publicaciones.getNumber());
		publicacionRespuesta.setMedidaPagina(publicaciones.getSize());
		publicacionRespuesta.setTotalElementos(publicaciones.getTotalElements());
		publicacionRespuesta.setTotalPaginas(publicaciones.getTotalPages());
		publicacionRespuesta.setUltima(publicaciones.isLast());
		
		return publicacionRespuesta;
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
	public void eliminarPublicacion(Long id) {
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
