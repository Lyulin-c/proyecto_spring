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
import com.spring.proyecto.entity.Articulo;
import com.spring.proyecto.service.ArticuloService;
import com.spring.proyecto.service.CompraService;

@RestController
@CrossOrigin(origins="*",methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/articulos")
public class ArticuloController {

	@Autowired
	private ArticuloService servicio;
	
	@Autowired
	private CompraService compraServicio;
	
	@GetMapping
	public ResponseEntity<?> allArticulo(){
		Map<String,String>response=new HashMap<>();
		
		if(servicio.allArticulos().isEmpty()) {
			response.put("Error", "No hay registros en este momento");
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Articulo>>(servicio.allArticulos(), HttpStatus.OK);
		}	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getArticuloById(@PathVariable Long id) throws JsonProcessingException{
		Map<String,String>response=new HashMap<>();
		if (servicio.findArticulo(id)==null) {
			response.put("Error", "No existe el registro id: "+id);
			return new ResponseEntity<Map<String, String>>(response,HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Articulo>(servicio.findArticulo(id), HttpStatus.OK);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> saveArticulo(@RequestBody Articulo articulo) {
		Map<String,String>response=new HashMap<>();
		if (articulo.isEmpty()) {
			response.put("Error","debes pasar datos del articulo" );
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
		} else {
			try {	
				Articulo articuloGuardado= servicio.saveArticulo(articulo);
				return new ResponseEntity<Articulo>(articuloGuardado, HttpStatus.CREATED);
			} catch (DataAccessException e) {
				response.put("Mensaje","Error al realizar alta en base de datos");
				response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateArticulo(@PathVariable Long id,@RequestBody Articulo articulo) {
		Map<String,String>response= new HashMap<>();
		Articulo articuloUpdate;
		
		if (servicio.findArticulo(id)==null) {
			response.put("Error","No existe el registro con el id: "+id );
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
		} else {
			if(articulo.isEmpty()) {		
				response.put("Mensaje","debe pasar datos de articulo");
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
			}else {
				try {
					articuloUpdate = servicio.findArticulo(id);
					articuloUpdate.setNombre(articulo.getNombre());
					articuloUpdate.setDescripcion(articulo.getDescripcion());
					articuloUpdate.setUnidad_precio(articulo.getUnidad_precio());
					articuloUpdate.setUnidad_stock(articulo.getUnidad_stock());
					articuloUpdate.setTipo(articulo.getTipo());
					articuloUpdate.setProveedor(articulo.getProveedor());
					articuloUpdate.setFecha(articulo.getFecha());
				} catch (DataAccessException e) {
					response.put("Mensaje","Error al actualizar en base de datos");
					response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
				}
				
				try {
					Articulo articuloActualizado= servicio.saveArticulo(articuloUpdate);
					return new ResponseEntity<Articulo>( articuloActualizado, HttpStatus.OK);
				} catch (DataAccessException e) {
					response.put("Mensaje","Error al actualizar en base de datos");
					response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteArticulo(@PathVariable Long id) {
		Map<String,String>response= new HashMap<>();
		Articulo articuloBorrado;
		
		if(servicio.findArticulo(id)==null) {	
			response.put("Error","No existe el registro con el id: "+id );
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
		}else {
			try {
				articuloBorrado=servicio.findArticulo(id);
			} catch (DataAccessException e) {
				response.put("Mensaje","Error al eliminar en base de datos");
				response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
			}
			
			try {
				compraServicio.deleteComprasByArticulosId(id);
			} catch (DataAccessException e) {
				response.put("Mensaje","Error al eliminar en base de datos");
				response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			try {
				servicio.deleteArticulo(id);
				return new ResponseEntity<Articulo>(articuloBorrado,HttpStatus.OK);
			} catch (DataAccessException e) {
				response.put("Mensaje","Error al eliminar en base de datos");
				response.put("Error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
	}

	
}
