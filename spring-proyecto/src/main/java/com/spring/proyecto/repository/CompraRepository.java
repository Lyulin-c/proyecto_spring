package com.spring.proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.proyecto.entity.Compra;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long>{

	 void deleteByCliente_Id(Long clienteId);
	 void deleteByArticulos_Id(Long articuloId);
}
