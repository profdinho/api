package com.profdinho.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.GeocodingResult;
import com.profdinho.api.service.MapsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "GoogleMaps")
@RequestMapping({"/maps"})
public class MapsController {
	
	private static final Logger logger = LoggerFactory.getLogger(MapsController.class);
	
	@Autowired
	MapsService mapsService;
	
	@ApiOperation(
			value = "Listar os endereços", 
			response = GeocodingResult.class, 
			notes = "Essa operação lista os endereços.")
	@GetMapping(path = "/{endereco}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeocodingResult> buscarGeolocalizacao(@PathVariable String endereco){
		GeocodingResult result = new GeocodingResult();
		try {
			result = mapsService.buscarGeolocalizacao(endereco);
			logger.info("Maps: {}", result);
		}
		catch (Exception e) {
			logger.error("Maps: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok(result);		
	}

}
