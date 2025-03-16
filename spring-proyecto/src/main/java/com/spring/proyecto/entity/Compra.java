package com.spring.proyecto.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="compra")
public class Compra implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cliente_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Cliente cliente;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articulo_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Articulo articulos;
	@Column(name="fecha_compra")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private int cantidad;
	private double total;
	private double iva;
	private double total_iva;
	

	public boolean isEmpty() {
		return cliente == null && articulos == null;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Articulo getArticulos() {
		return articulos;
	}
	public void setArticulos(Articulo articulos) {
		this.articulos = articulos;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public double getTotal_iva() {
		return total_iva;
	}
	public void setTotal_iva(double total_iva) {
		this.total_iva = total_iva;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
