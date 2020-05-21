package com.profdinho.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.profdinho.api.model.Endereco;
import com.profdinho.api.service.EnderecoService;

@RestController
public class EnderecoController {
	
	private static final Logger logger = LoggerFactory.getLogger(EnderecoController.class);
	
	@Autowired EnderecoService enderecoService;
	
	@GetMapping(path = "/endereco/{cep}", produces = { "application/json" })
	public ResponseEntity<Endereco> buscarEndereco(@PathVariable("cep") String cep) {
		Endereco endereco = new Endereco();
		try {
			endereco = enderecoService.buscarEndereco(cep);
			logger.info("Endereço retornado: {}", endereco);
		}
		catch(Exception e) {
			logger.error("Erro: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok(endereco);
	}
	
	@GetMapping(path = "/endereco/{uf}/{cidade}/{logradouro}", produces = { "application/json" })
	public ResponseEntity<List<Endereco>> buscarCEP(@PathVariable("uf") String uf, @PathVariable("cidade") String cidade, @PathVariable("logradouro") String logradouro){
		List<Endereco> listaEnderecos = new ArrayList<Endereco>();
		try {
			listaEnderecos = enderecoService.buscarCEP(uf, cidade, logradouro);
			logger.info("Endereços retornados: {}", listaEnderecos);
		}
		catch(Exception e) {
			logger.error("Erro: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}		
		return ResponseEntity.ok(listaEnderecos);
	}

}
