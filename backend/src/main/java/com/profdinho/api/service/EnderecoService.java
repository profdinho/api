package com.profdinho.api.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.profdinho.api.model.Endereco;

@Service
public class EnderecoService {
	
	private final static String URL = "https://viacep.com.br/ws/";
	private final static String URL_CEP = URL + "{cep}/json";
	private final static String URL_END = URL + "{uf}/{cid}/{log}/json";
	
	RestTemplate restTemplate = new RestTemplate();
	
	public Endereco buscarEndereco(String cep) {
		Endereco endereco = new Endereco();
		//return restTemplate.exchange(URL_CEP.replace("{cep}", cep), HttpMethod.GET, null, new ParameterizedTypeReference<List<Endereco>>(){}).getBody();
		endereco = restTemplate.getForObject(URL_CEP.replace("{cep}", cep), Endereco.class);
		return endereco;
	}
	
	public List<Endereco> buscarCEP(String uf, String cidade, String logradouro){
		System.out.println(URL_END.replace("{uf}", uf).replace("{cid}", cidade).replace("{log}", logradouro));
		return restTemplate.exchange(URL_END.replace("{uf}", uf).replace("{cid}", cidade).replace("{log}", logradouro),
									HttpMethod.GET,
									null,
									new ParameterizedTypeReference<List<Endereco>>(){}).getBody();
		
	}
}
