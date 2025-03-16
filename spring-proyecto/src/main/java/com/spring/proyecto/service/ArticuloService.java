package com.spring.proyecto.service;

import java.util.List;

import com.spring.proyecto.entity.Articulo;

public interface ArticuloService {

	//metodo para buscar todos los articulos
	public List<Articulo> allArticulos();
	
	//metodo para buscar un articulo por id
	public Articulo findArticulo(Long id);
	
	//metodo para guardar un articulo
	public Articulo saveArticulo(Articulo articulo);
	
	//metodo para borrar un articulo por id
	public void deleteArticulo(Long id);
}
