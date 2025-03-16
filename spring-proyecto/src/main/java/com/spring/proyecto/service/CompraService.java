package com.spring.proyecto.service;

import java.util.List;

import com.spring.proyecto.entity.Compra;

public interface CompraService {

	//metodo para buscar todas las compras
	public List<Compra> allCompras();
	
	//metodo para buscar una compra por id
	public Compra findCompra(Long id);
	
	//metodo para guardar una compra
	public Compra saveCompra(Compra compra);
	
	//metodo para borrar una compra por id
	public void deleteCompra(Long id);
	
	//metodo para borrar una compra por id de cliente
	public void deleteComprasByClienteId(Long clienteId);

	//metodo para borrar una compra por id de cliente
	public void deleteComprasByArticulosId(Long articuloId);
}
