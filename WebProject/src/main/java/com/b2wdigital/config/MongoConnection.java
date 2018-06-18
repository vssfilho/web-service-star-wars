package com.b2wdigital.config;

import java.util.Arrays;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

	private static MongoDatabase db;
	private static MongoClient client;
	
	public MongoConnection() {

		MongoConnection.client = MongoClients.create(
		        MongoClientSettings.builder()
		                .applyToClusterSettings(builder ->
		                		// uri e porta do banco de dados
		                        builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
		                .build());
		 // nome do banco de dados
		 MongoConnection.db = MongoConnection.client.getDatabase("db_star_wars");
		 
	}

	public MongoDatabase getDb() {
		return db;
	}
	
	public void close() {
		MongoConnection.client.close();
	}
	
}
