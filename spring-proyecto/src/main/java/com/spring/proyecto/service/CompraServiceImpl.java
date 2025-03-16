package com.spring.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.proyecto.entity.Compra;
import com.spring.proyecto.repository.CompraRepository;

@Service
public class CompraServiceImpl implements CompraService{
	
	@Autowired
	private CompraRepository compraRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Compra> allCompras() {
		return (List<Compra>) compraRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Compra findCompra(Long id) {
		return compraRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Compra saveCompra(Compra compra) {
		return compraRepository.save(compra);
	}

	@Override
	@Transactional
	public void deleteCompra(Long id) {
		compraRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void deleteComprasByClienteId(Long clienteId) {
		compraRepository.deleteByCliente_Id(clienteId);
	}

	@Override
	@Transactional
	public void deleteComprasByArticulosId(Long articuloId) {
		compraRepository.deleteByArticulos_Id(articuloId);
	}

}
