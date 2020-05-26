package com.profdinho.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profdinho.api.model.Contato;
import com.profdinho.api.service.ContatoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Contatos")
@RequestMapping({"/contatos"})
public class ContatoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ContatoController.class);
	
	@Autowired
	ContatoService contatoService;
	
	@ApiOperation(
			value = "Listar os contatos", 
			response = List.class, 
			notes = "Essa operação lista os contatos.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contato>> buscarContatos(){
		List<Contato> listaContatos = new ArrayList<Contato>();
		try {
			listaContatos = contatoService.buscarContatos();
			logger.info("Contatos: {}", listaContatos);
		}
		catch(Exception e) {
			logger.error("Erro: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok(listaContatos);
	}
	
	@ApiOperation(
			value = "Buscar um contato", 
			response = Contato.class, 
			notes = "Essa operação busca um contato pelo ID.")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contato> buscarContato(@PathVariable Long id){
		Contato contato = new Contato();
		try {
			contato = contatoService.buscarContato(id);
			logger.info("Contato: {}", contato);
		}
		catch(Exception e) {
			logger.error("Erro: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok(contato);
	}
	
	@ApiOperation(
			value = "Adicionar um contato", 
			response = Contato.class, 
			notes = "Essa operação adiciona um contato.")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contato> adicionarContato(@RequestBody Contato contato) {
		Contato novoContato = new Contato();
		try {
			novoContato = contatoService.adicionarContato(contato);
			logger.info("Novo Contato: {}", novoContato);
		}
		catch(Exception e) {
			logger.error("Erro: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok(novoContato);
	}
	
	@ApiOperation(
			value = "Adicionar um contato", 
			response = Contato.class, 
			notes = "Essa operação adiciona um contato.")
	@PutMapping(value="/{id}")
	public ResponseEntity<Contato> atualizarContato(@PathVariable("id") Long id, @RequestBody Contato contato) {
		ResponseEntity<Contato> contatoAtualizado = null;
		try {
			contatoAtualizado = contatoService.atualizarContato(id, contato);
			logger.info("Contato atualizado: {}", contatoAtualizado.getBody());
		}
		catch(Exception e) {
			logger.error("Erro: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return contatoAtualizado;
	}
	
	@ApiOperation(
			value = "Adicionar um contato", 
			response = Contato.class, 
			notes = "Essa operação adiciona um contato.")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> apagarContato(@PathVariable("id") Long id){
		ResponseEntity<?> contatoApagado = null;
		try {
			contatoApagado = contatoService.apagarContato(id);
			logger.info("Contato apagado: {}", id);
		}
		catch(Exception e) {
			logger.error("Erro: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return contatoApagado;
	}

}
