package com.b2wdigital.starwars.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.b2wdigital.starwars.api.element.SwApiRootElement;

// Client para conexão e busca dos dados do planeta no star wars api
public class ClientSWApi {

	Client client;
	// Foi necessario informar o formato de retorno pois sem isso a api retorna um página html
	String uri = "https://swapi.co/api/planets/?search=:planeta&format=json";
	
	public ClientSWApi() {
		this.client = ClientBuilder.newClient();
	}
	
	
	public SwApiRootElement buscaDadosPlaneta(String planeta) {
		return client.target(uri.replace(":planeta", planeta))
		.request(MediaType.APPLICATION_JSON)
		.get(SwApiRootElement.class);
	}
}
