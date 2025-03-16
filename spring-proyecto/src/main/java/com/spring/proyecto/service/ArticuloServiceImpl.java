package com.spring.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.proyecto.entity.Articulo;
import com.spring.proyecto.repository.ArticuloRepository;

@Service
public class ArticuloServiceImpl implements ArticuloService{

	@Autowired
	private ArticuloRepository articuloRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Articulo> allArticulos() {
		return (List<Articulo>) articuloRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo findArticulo(Long id) {
		return articuloRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Articulo saveArticulo(Articulo articulo) {
		return articuloRepository.save(articulo);
	}

	@Override
	@Transactional
	public void deleteArticulo(Long id) {
		articuloRepository.deleteById(id);
	}

}
