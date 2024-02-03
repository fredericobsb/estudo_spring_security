package com.estudo.springsecurity.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.springsecurity.dto.CadastradorDto;

@RestController
@RequestMapping(value="/api/v1/teste",  produces = {"application/json"})
public class SegurancaController {

	@PostMapping
	public String cadastradorDto(@RequestBody CadastradorDto dto) {
		System.out.println("Nome que veio no dto ==> " + dto.getNome());
		System.out.println("CPF que veio no dto ==> " + dto.getCpf());
		return " ===> @PostMapping cadastrador => nome =" + dto.getNome();
	}
	
	@GetMapping
	public String recuperadorDto() {
		return " ===> @GetMapping recuperadorDto()";
	}
	
	@DeleteMapping
	public String deletadorDeDto() {
		return " ===> @DeleteMapping deletarDeDto()";
	}
}
