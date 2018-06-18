package com.b2wdigital.starwars.mongo.collection;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.b2wdigital.config.MongoConnection;
import com.b2wdigital.starwars.rest.domain.PlanetaDomain;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

public class PlanetaCollection extends MongoConnection {

	MongoCollection<Document> collection;
	
	public PlanetaCollection () {
		collection = super.getDb().getCollection("planeta");
	}
	
	public MongoCursor<Document> listAll() {
		return this.collection.find().iterator();
	}
	
	public void adicionar(Document planeta) {
		this.collection.insertOne(planeta);
	}
	
	public MongoCursor<Document> findBy(PlanetaDomain planeta) {
		// Como a aplicação possui consulta pelo id ou pelo nome,
		// o filtro utiliza essas duas variaveis na consulta sendo deixado de lado o clima e o terreno
		Bson filters = Filters.or(
			Filters.eq("nome", planeta.getNome()),
			Filters.eq("id", planeta.getId())
		);
		
		return this.collection.find(filters).iterator();
	}
	

	public DeleteResult deleteById(PlanetaDomain planeta) {
		Bson filters = Filters.eq("id", planeta.getId());
		return this.collection.deleteOne(filters);
	}
	
}
