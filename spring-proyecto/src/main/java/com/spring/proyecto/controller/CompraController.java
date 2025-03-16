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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.proyecto.entity.Compra;
import com.spring.proyecto.service.CompraService;

@RestController
@CrossOrigin(origins="*",methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/compras")
public class CompraController {

	@Autowired
	private CompraService servicio;
	
	@GetMapping
	public ResponseEntity<?> allCompra(){
		Map<String,String>response=new HashMap<>();
		
		if(servicio.allCompras().isEmpty()) {
			response.put("Error", "No hay registros en este momento");
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Compra>>(servicio.allCompras(), HttpStatus.OK);
		}	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCompraById(@PathVariable Long id) throws JsonProcessingException{
		Map<String,String>response=new HashMap<>();
		if (servicio.findCompra(id)==null) {
			response.put("Error", "No existe el registro id: "+id);
			return new ResponseEntity<Map<String, String>>(response,HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Compra>(servicio.findCompra(id), HttpStatus.OK);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> saveCompra(@RequestBody Compra compra) {
		Map<String,String>response=new HashMap<>();
		if (compra.isEmpty()) {
			response.put("Error","debes pasar datos de compra" );
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
		} else {
			try {	
				Compra compraGuardado= servicio.saveCompra(compra);
				return new ResponseEntity<Compra>(compraGuardado, HttpStatus.CREATED);
			} catch (DataAccessException e) {
				response.put("Mensaje","Error al realizar alta en base de datos");
				response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCompra(@PathVariable Long id,@RequestBody Compra compra) {
		Map<String,String>response= new HashMap<>();
		Compra compraUpdate;
		
		if (servicio.findCompra(id)==null) {
			response.put("Error","No existe el registro con el id: "+id );
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
		} else {
			if(compra.isEmpty()) {		
				response.put("Mensaje","debe pasar datos de compra");
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
			}else {
				try {
					compraUpdate = servicio.findCompra(id);
					compraUpdate.setCliente(compra.getCliente());
					compraUpdate.setArticulos(compra.getArticulos());
					compraUpdate.setFecha(compra.getFecha());
					compraUpdate.setCantidad(compra.getCantidad());
					compraUpdate.setTotal(compra.getTotal());
					compraUpdate.setIva(compra.getIva());
					compraUpdate.setTotal_iva(compra.getTotal_iva());
				} catch (DataAccessException e) {
					response.put("Mensaje","Error al actualizar en base de datos");
					response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
				}
				
				try {
					Compra compraActualizado= servicio.saveCompra(compraUpdate);
					return new ResponseEntity<Compra>( compraActualizado, HttpStatus.OK);
				} catch (DataAccessException e) {
					response.put("Mensaje","Error al actualizar en base de datos");
					response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCompra(@PathVariable Long id) {
		Map<String,String>response= new HashMap<>();
		Compra compraBorrado;
		
		if(servicio.findCompra(id)==null) {	
			response.put("Error","No existe el registro con el id: "+id );
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
		}else {
			try {
				compraBorrado=servicio.findCompra(id);
			} catch (DataAccessException e) {
				response.put("Mensaje","Error al eliminar en base de datos");
				response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
			}
			
			try {
				servicio.deleteCompra(id);
				return new ResponseEntity<Compra>(compraBorrado,HttpStatus.OK);
			} catch (DataAccessException e) {
				response.put("Mensaje","Error al eliminar en base de datos");
				response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
	}
	
}
