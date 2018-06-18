package com.b2wdigital.util;

import org.bson.Document;

import com.b2wdigital.config.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;

public class MongoSequence extends MongoConnection {

	MongoCollection<Document> collection;
	
	public MongoSequence () {
		collection = super.getDb().getCollection("sequences");
	}
	
	/**
	 * Como o Mongo DB não possui uma opção nativa de para sequences,
	 * foi necessario gerar o processo de forma manual */
	public Integer getNextSequence(String collection) {
		Document sequenceDocument = this.collection.findOneAndUpdate(
				new Document("_id", collection), 
				new Document("$inc", new Document("sequence_value", 1)), 
				new FindOneAndUpdateOptions().upsert(true).returnDocument(ReturnDocument.AFTER));
		return (int) Double.parseDouble(sequenceDocument.get("sequence_value").toString());
	}

}
