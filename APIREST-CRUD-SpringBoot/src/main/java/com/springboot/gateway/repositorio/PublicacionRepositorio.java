 package com.springboot.gateway.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.gateway.entidades.Publicacion;

public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long>{

}
