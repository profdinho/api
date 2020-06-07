package com.profdinho.api.service;

import java.io.IOException;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

@Service
public class MapsService {
	
	private final static String API_KEY = "AIzaSyCLi5QtdJFijmXxsX07HRVZTmqQmOfcX2k";
	
	public GeocodingResult buscarGeolocalizacao(String endereco) {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(API_KEY)
			    .build();
		GeocodingResult result = new GeocodingResult();
		GeocodingApiRequest req = GeocodingApi.newRequest(context).address(endereco);
		
		
		try {
			result = req.await()[0];
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
