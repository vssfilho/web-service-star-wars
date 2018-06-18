package com.b2wdigital.starwars.rest.resource;

import java.io.IOException;
import java.util.List;

import org.bson.Document;

import com.b2wdigital.starwars.convert.PlanetaConvert;
import com.b2wdigital.starwars.mongo.collection.PlanetaCollection;
import com.b2wdigital.starwars.rest.domain.PlanetaDomain;
import com.b2wdigital.util.MongoSequence;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;

public class PlanetaResource {

	private PlanetaCollection planetaCollection;
	private PlanetaConvert planetaConverter;
	
	public PlanetaResource() {
		this.planetaCollection = new PlanetaCollection();
		this.planetaConverter = new PlanetaConvert();
	}
	
	public void adicionar(PlanetaDomain planeta) throws JsonProcessingException, IOException {
		MongoSequence sequence = new MongoSequence();
		// Carrega sequence para cadastro do planeta
		planeta.setId(sequence.getNextSequence("planeta"));
		// Converte a classe de domain para document para realizar insert no banco de dados
		this.planetaCollection.adicionar(this.planetaConverter.toDocumen(planeta));
	}
	
	public List<PlanetaDomain> listar() throws JsonProcessingException, IOException {
		List<PlanetaDomain> listDomain = null;
		MongoCursor<Document> listDocument = this.planetaCollection.listAll();
		// Converte os dados trazidos do banco de dados para a classe de domain
		listDomain = this.planetaConverter.toListDomain(listDocument);
		return listDomain;
	}
	
	public List<PlanetaDomain> buscar(PlanetaDomain planeta) throws JsonProcessingException, IOException {
		List<PlanetaDomain> listDomain = null;
		// Para realizar uma consulta é necessário passar o id ou o nome do planeta
		MongoCursor<Document> listDocument = this.planetaCollection.findBy(planeta);
		listDomain = this.planetaConverter.toListDomain(listDocument);
		return listDomain;
	}

	public Long remover(PlanetaDomain planeta) throws JsonProcessingException, IOException {
		// Necessário informar o id para realizar o delete de um planeta
		DeleteResult result = this.planetaCollection.deleteById(planeta);
		return result.getDeletedCount();
	}
}
