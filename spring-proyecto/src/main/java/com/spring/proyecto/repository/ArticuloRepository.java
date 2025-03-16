package com.spring.proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.proyecto.entity.Articulo;


@Repository
public interface ArticuloRepository extends CrudRepository<Articulo, Long>{

}
