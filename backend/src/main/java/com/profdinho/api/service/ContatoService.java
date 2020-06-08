package com.profdinho.api.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

import com.profdinho.api.model.Contato;
import com.profdinho.api.repository.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired ContatoRepository contatoRepo;

	@Cacheable(cacheNames = "Contato", key="#root.method.name")
    public List<Contato> buscarContatos() {
		List<Contato> lista = new ArrayList<Contato>();
		lista = (List<Contato>) contatoRepo.findAll();
		return lista;
	}

	@Cacheable(cacheNames = "Contato", key="#identifier")
	public Contato buscarContato(Long id) {
		
		return contatoRepo.findById(id).orElse(new Contato());
	}
	
	@CacheEvict(cacheNames = "Contato", allEntries = true)
	public Contato adicionarContato(Contato contato) {
		return contatoRepo.save(contato);
	}

	@CachePut(cacheNames = "Contato", key="#company.getIdentifier()")
	public ResponseEntity<Contato> atualizarContato(Long id, Contato contato) {
		return contatoRepo.findById(id)
						.map(registro -> {
							registro.setNome(contato.getNome());
							registro.setEmail(contato.getEmail());
							registro.setCelular(contato.getCelular());
							registro.setDataNascimento(contato.getDataNascimento());
							Contato atualizado = contatoRepo.save(registro);
							return ResponseEntity.ok().body(atualizado);
						})
						.orElse(ResponseEntity.notFound().build());
	}

	@CacheEvict(cacheNames = "Contato", key="#identifier")
	public ResponseEntity<?> apagarContato(Long id) {
		return contatoRepo.findById(id)
						.map(registro -> {
							contatoRepo.deleteById(id);
							return ResponseEntity.ok().build();
						})
						.orElse(ResponseEntity.notFound().build());
	}

}
