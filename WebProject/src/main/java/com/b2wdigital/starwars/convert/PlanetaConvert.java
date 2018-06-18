package com.b2wdigital.starwars.convert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.WebTarget;

import org.bson.Document;

import com.b2wdigital.starwars.api.ClientSWApi;
import com.b2wdigital.starwars.api.element.SwApiRootElement;
import com.b2wdigital.starwars.rest.domain.PlanetaDomain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCursor;

/**
 * Classe que converte os dados de domain para document do mongo db e vice versa  */
public class PlanetaConvert {

	ObjectMapper mapper;
	ClientSWApi client;
	WebTarget webTarget;
	
	public PlanetaConvert() {
		this.mapper = new ObjectMapper();
		this.client = new ClientSWApi();
	}
	
	/**
	 * Converte o domain para document para ser salvo no banco de dados */
	public Document toDocumen(PlanetaDomain planeta) {
		
		Document doc = new Document("id", planeta.getId())
				.append("nome", planeta.getNome())
				.append("clima", planeta.getClima())
				.append("terreno", planeta.getTerreno());
		
		return doc;
		
	}
	
	/**
	 * Converte string json para domain para ser utilizado na aplicação */
	public PlanetaDomain toDomain(String json) throws JsonProcessingException, IOException {
		
		PlanetaDomain domain = new PlanetaDomain();
		JsonNode rootNode = this.mapper.readTree(json);
		
		domain.setId(rootNode.get("id").asInt());
		domain.setNome(rootNode.get("nome").asText());
		
		rootNode.get("clima").forEach(clima -> {
			domain.addClima(clima.asText());
		});

		rootNode.get("terreno").forEach(terreno -> {
			domain.addTerreno(terreno.asText());
		});
		
		// Consulta a api para trazer a os dados do planeta
		SwApiRootElement result = client.buscaDadosPlaneta(domain.getNome());

		// Verifica a consulta trouxe dados e busca o numero de filmes que o planeta apareceu
		if(result != null && result.getCount() > 0) {
			domain.setNumeroDeFilmes(result.getResults().get(0).getFilms().size());
		} 
		
		return domain;
	}
	
	/**
	 * Converte um list de document para um list de domain */
	public List<PlanetaDomain> toListDomain(MongoCursor<Document> listDocument) throws JsonProcessingException, IOException{
		List<PlanetaDomain> listDomain = new ArrayList<>();
		try {
		    while (listDocument.hasNext()) {
		    	listDomain.add(this.toDomain(listDocument.next().toJson()));
		    }
		} finally {
			listDocument.close();
		}
		return listDomain;
	}
	
}
