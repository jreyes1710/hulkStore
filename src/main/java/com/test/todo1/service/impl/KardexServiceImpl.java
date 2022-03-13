package com.test.todo1.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.todo1.entities.Kardex;
import com.test.todo1.entities.Producto;
import com.test.todo1.repositories.KardexRepository;

@Service
public class KardexServiceImpl {

	@Autowired
	private KardexRepository kardexRepo;

	public List<Kardex> listarKardex() {
		List<Kardex> listarKardex = new ArrayList<>();
		listarKardex = kardexRepo.findAll();
		return listarKardex;
	}

	@Transactional
	public Kardex registrarKardex(Producto producto) {
		Kardex newKardex = initKardex(producto);
		return kardexRepo.save(newKardex);
	}

	private Kardex initKardex(Producto producto) {
		Kardex kardexkObj = new Kardex();
		List<Kardex> listarKardexProducto = kardexRepo.listarKardexProducto(producto.getIdProducto());
		listarKardexProducto.forEach(p -> {
			if (p.getStockActual() > producto.getCantidad()) {
				kardexkObj.setTipo("IN");
			} else {
				kardexkObj.setTipo("EG");
			}
		});
		kardexkObj.setTipoDocumento("FA");
		kardexkObj.setCantidad(producto.getCantidad());
		kardexkObj.setPrecioUnitario(producto.getPrecioUnitario());

		return kardexkObj;
	}

}
