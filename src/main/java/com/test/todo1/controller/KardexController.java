package com.test.todo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.todo1.entities.Kardex;
import com.test.todo1.exceptions.ModeloNotFoundException;
import com.test.todo1.service.impl.KardexServiceImpl;

import io.swagger.annotations.Api;

@Api
@RestController
public class KardexController {

	@Autowired
	private KardexServiceImpl kardexService;

	@GetMapping("/listarKardex")
	public ResponseEntity<List<Kardex>> findAll() {
		List<Kardex> listarKardex = kardexService.listarKardex();
		if (listarKardex.isEmpty()) {
			throw new ModeloNotFoundException("No Existen Movimientos en el Kardex");

		}
		return new ResponseEntity<List<Kardex>>(listarKardex, HttpStatus.OK);
	}

}
