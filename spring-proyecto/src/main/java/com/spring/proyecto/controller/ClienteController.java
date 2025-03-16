package com.spring.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.proyecto.entity.Cliente;
import com.spring.proyecto.service.ClienteService;
import com.spring.proyecto.service.CompraService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins="*",methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService servicio;
	
	@Autowired
	private CompraService compraServicio;
	
	@GetMapping
	public ResponseEntity<?> allCliente(){
		Map<String,String>response=new HashMap<>();
		
		if(servicio.allClient().isEmpty()) {
			response.put("Error", "No hay registros en este momento");
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Cliente>>(servicio.allClient(), HttpStatus.OK);
		}	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getClienteById(@PathVariable Long id) throws JsonProcessingException{
		Map<String,String>response=new HashMap<>();
		if (servicio.findClient(id)==null) {
			response.put("Error", "No existe el registro id: "+id);
			return new ResponseEntity<Map<String, String>>(response,HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Cliente>(servicio.findClient(id), HttpStatus.OK);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
		Map<String,String>response=new HashMap<>();
		if (cliente.isEmpty()) {
			response.put("Error","debes pasar datos de cliente" );
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
		} else {
			try {	
				Cliente clienteGuardado= servicio.saveClient(cliente);
				return new ResponseEntity<Cliente>(clienteGuardado, HttpStatus.CREATED);
			} catch (DataAccessException e) {
				response.put("Mensaje","Error al realizar alta en base de datos");
				response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateClient(@PathVariable Long id,@RequestBody Cliente cliente) {
		Map<String,String>response= new HashMap<>();
		Cliente clienteUpdate;
		
		if (servicio.findClient(id)==null) {
			response.put("Error","No existe el registro con el id: "+id );
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
		} else {
			if(cliente.isEmpty()) {		
				response.put("Mensaje","debe pasar datos de cliente");
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
			}else {
				try {
					clienteUpdate = servicio.findClient(id);
					clienteUpdate.setNombre(cliente.getNombre());
					clienteUpdate.setApellido(cliente.getApellido());
					clienteUpdate.setEmpresa(cliente.getEmpresa());
					clienteUpdate.setPuesto(cliente.getPuesto());
					clienteUpdate.setDireccion(cliente.getDireccion());
					clienteUpdate.setCodigo_postal(cliente.getCodigo_postal());
					clienteUpdate.setProvincia(cliente.getProvincia());
					clienteUpdate.setTelefono(cliente.getTelefono());
					clienteUpdate.setFecha_nac(cliente.getFecha_nac());
				} catch (DataAccessException e) {
					response.put("Mensaje","Error al actualizar en base de datos");
					response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
				}
				
				try {
					Cliente clienteActualizado= servicio.saveClient(clienteUpdate);
					return new ResponseEntity<Cliente>( clienteActualizado, HttpStatus.OK);
				} catch (DataAccessException e) {
					response.put("Mensaje","Error al actualizar en base de datos");
					response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable Long id) {
		Map<String,String>response= new HashMap<>();
		Cliente clienteBorrado;
		
		if(servicio.findClient(id)==null) {	
			response.put("Error","No existe el registro con el id: "+id );
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
		}else {
			try {
				clienteBorrado=servicio.findClient(id);
			} catch (DataAccessException e) {
				response.put("Mensaje","Error al eliminar en base de datos");
				response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
			}
			
			try {
				compraServicio.deleteComprasByClienteId(id);
			} catch (DataAccessException e) {
				response.put("Mensaje","Error al eliminar en base de datos");
				response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			try {
				servicio.deleteClient(id);
				return new ResponseEntity<Cliente>(clienteBorrado,HttpStatus.OK);
			} catch (DataAccessException e) {
				response.put("Mensaje","Error al eliminar en base de datos");
				response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
	}

}
