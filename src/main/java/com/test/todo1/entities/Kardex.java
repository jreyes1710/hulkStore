package com.test.todo1.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "KARDEX_TBL")
public class Kardex {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idKardex;

	@Column(name = "tipo", nullable = false, length = 1)
	private String tipo;

	@Column(name = "tipo_dodumento", nullable = false, length = 10)
	private String tipoDocumento;

	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;

	@Column(name = "precio_unitario", nullable = false)
	private BigDecimal precioUnitario;

	@Column(name = "stock_anterior", nullable = false)
	private Integer stockAnterior;

	@Column(name = "stock_actual", nullable = false)
	private Integer stockActual;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "kardex")
	private List<Producto> productos;

	public Long getIdKardex() {
		return idKardex;
	}

	public void setIdKardex(Long idKardex) {
		this.idKardex = idKardex;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Integer getStockAnterior() {
		return stockAnterior;
	}

	public void setStockAnterior(Integer stockAnterior) {
		this.stockAnterior = stockAnterior;
	}

	public Integer getStockActual() {
		return stockActual;
	}

	public void setStockActual(Integer stockActual) {
		this.stockActual = stockActual;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
