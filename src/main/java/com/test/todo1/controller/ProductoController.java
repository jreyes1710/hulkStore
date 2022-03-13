package com.test.todo1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.todo1.entities.Producto;
import com.test.todo1.exceptions.ModeloNotFoundException;
import com.test.todo1.service.impl.KardexServiceImpl;
import com.test.todo1.service.impl.ProductoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
public class ProductoController {

	@Autowired
	private ProductoServiceImpl productoService;

	@Autowired
	private KardexServiceImpl kardexService;

	@GetMapping("/listarProductos")
	public ResponseEntity<List<Producto>> findAll() {
		List<Producto> listarProductos = productoService.listarProductos();
		if (listarProductos.isEmpty()) {
			throw new ModeloNotFoundException("No se han registrado Productos");

		}
		return new ResponseEntity<List<Producto>>(listarProductos, HttpStatus.OK);
	}

	@GetMapping("/producto/{idProducto}")
	public ResponseEntity<Producto> findById(@PathVariable Long idProducto) {
		Producto producto = productoService.obtenerProdutoPorId(idProducto);
		if (producto == null) {
			throw new ModeloNotFoundException(" No se encontro el Producto con id: " + idProducto);

		}
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}

	@PostMapping("/crearProductos")
	public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
		productoService.guardar(producto);
		return new ResponseEntity<Producto>(producto, HttpStatus.CREATED);
	}

	@PutMapping("/actualizarProducto")
	public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto producto) throws Exception {
		Producto productoActualizar = productoService.obtenerProdutoPorId(producto.getIdProducto());
		if (productoActualizar == null) {
			productoService.guardar(producto);
			return new ResponseEntity<Producto>(producto, HttpStatus.CREATED);

		} else {
			productoService.guardar(producto);
			return new ResponseEntity<>(producto, HttpStatus.OK);
		}

	}
	@DeleteMapping("/producto/{idProducto}")
	public ResponseEntity<Void> eliminar(@PathVariable("idProducto") Long idProducto) throws Exception {
		Producto producto = productoService.obtenerProdutoPorId(idProducto);
		if (producto == null) {
			throw new ModeloNotFoundException(
					" No se encontro Producto con id: " + idProducto + " no se puede se eliminado");

		} else {
			productoService.eliminar(idProducto);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/comprarProducto")
	public ResponseEntity<Producto> comprarProducto(@RequestBody Producto producto) {
		Producto productoCompra = productoService.obtenerProdutoPorId(producto.getIdProducto());
		if (productoCompra != null) {
			if (productoCompra.getCantidad() > 0) {
				kardexService.registrarKardex(productoCompra);
			} else {
				throw new ModeloNotFoundException("No Tiene Stock, el producto:" + producto.getNombre());
			}
		} else {
			throw new ModeloNotFoundException("No Tiene Stock, el producto:" + producto.getNombre());

		}
		return new ResponseEntity<Producto>(producto, HttpStatus.CREATED);
	}
}
