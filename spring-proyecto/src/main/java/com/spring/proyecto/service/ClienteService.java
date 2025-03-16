package com.spring.proyecto.service;

import java.util.List;

import com.spring.proyecto.entity.Cliente;

public interface ClienteService {
	
	//metodo para buscar todos los clientes
	public List<Cliente> allClient();
	
	//metodo para buscar un cliente por id
	public Cliente findClient(Long id);
	
	//metodo para registrar un cliente
	public Cliente saveClient(Cliente cliente);
	
	//metodo para borrar un cliente por id
	public void deleteClient(Long id);
	
}
