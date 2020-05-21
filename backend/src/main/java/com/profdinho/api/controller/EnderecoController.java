package com.profdinho.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Endereco")
public class EnderecoController {
	
	private static final Logger logger = LoggerFactory.getLogger(EnderecoController.class);
	
	@Autowired EnderecoService enderecoService;
	
	@ApiOperation(
			value = "Buscar um endereço", 
			response = Endereco.class, 
			notes = "Essa operação busca um endereço através de um CEP informado.")
	@GetMapping(path = "/endereco/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@ApiOperation(
			value = "Buscar uma lista de CEPS",
			response = List.class,
			notes = "Essa operação busca uma lista de CEPs através de um endereço informado.")
	@GetMapping(path = "/endereco/{uf}/{cidade}/{logradouro}", produces = MediaType.APPLICATION_JSON_VALUE)
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
