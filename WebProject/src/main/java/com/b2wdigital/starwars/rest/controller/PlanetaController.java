package com.b2wdigital.starwars.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.b2wdigital.starwars.rest.domain.PlanetaDomain;
import com.b2wdigital.starwars.rest.resource.PlanetaResource;
import com.b2wdigital.util.Response;

@RestController
@RequestMapping("/planeta")
public class PlanetaController {
	
	PlanetaResource planetaResource;
	
	public PlanetaController() {
		this.planetaResource = new PlanetaResource();
	}
	
	// Index do webservice e traz alguns dados para utilização da aplicação
	@RequestMapping(value = { "/" , ""  })
	public String welcome() {
        return "Bem-vindo ao Rest Star Wars.<br/>"
        		+ "Esse webservice possui as seguintes chamadas:<br/>"
        		+ "<ul>"
        			+ "<li>/adicionar | PUT: para incluir um planeta. "
        				+ "	<ul>"
        					+ "<li>Envia seguinte estrutura json para inserir um novo planeta:</li>"
        					+ "<li>{nome:\"nome_do_planeta\", clima:[\"clima_1\", \"clima_2\", \"clima_n\"], terreno:[\"terreno_1\", \"terreno_2\", \"terreno_n\"]}</li>"
        					+ "</ul>"
					+ "</li>"
					+ "<li>/listar | GET: para listar todos os planetas cadastrados</li>"
					+ "<li>/nome/nome_do_planeta | GET: para buscar o planeta pelo nome informado</li>"
					+ "<li>/id/id_do_planeta | GET: para buscar o planeta pelo id informado</li>"
					+ "<li>/remover/id_do_planeta | DELETE: para remover o planeta com o respectivo id</li>"
        		+ "</ul>";
    }
	
	// Aplicação recebe um json para ser convertido e salvo no banco de dados
	@RequestMapping(value = "/adicionar", method = RequestMethod.PUT)
	public Response adicionar(@RequestBody PlanetaDomain planeta) {
		try {
			planetaResource.adicionar(planeta);
			return Response.builder().setSuccess().setData("Planeta salvo com sucesso").build();
		} catch(Exception e) {
			return Response.builder().setError().setData("Erro cadastrar o planeta").build();
		}
	}
	
	// Retornar todos os planetas cadastrados na banco de dados junto com as suas participações em filmes
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public Response listar() {
		try {
			List<PlanetaDomain> listPlanetas = planetaResource.listar();
			return Response.builder().setSuccess().setData(listPlanetas).build();
		} catch(Exception e) {
			return Response.builder().setError().setData("Erro ao tentar listar os planetas cadastrados").build();
		}
	}
	
	// A busca é uma consulta generica, porém essa request recebe o nome do planeta
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	public Response buscarPorNome(@PathVariable String nome) {
		PlanetaDomain planeta = new PlanetaDomain();
		try {
			planeta.setNome(nome);
			List<PlanetaDomain> listPlanetas = planetaResource.buscar(planeta);
			return Response.builder().setSuccess().setData(listPlanetas).build();
		} catch(Exception e) {
			return Response.builder().setError().setData("Erro ao tentar buscar o planeta informado").build();
		}
	}
	
	// A busca é uma consulta generica, porém essa request recebe o id do planeta
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public Response buscarPorId(@PathVariable Integer id) {
		PlanetaDomain planeta = new PlanetaDomain();
		try {
			planeta.setId(id);
			List<PlanetaDomain> listPlanetas = planetaResource.buscar(planeta);
			return Response.builder().setSuccess().setData(listPlanetas).build();
		} catch(Exception e) {
			return Response.builder().setError().setData("Erro ao tentar buscar o planeta informado").build();
		}
	}
	
	// Para realizar o delete de um planeta é necessario receber o id do item
	@RequestMapping(value = "/remover/{id}", method = RequestMethod.DELETE)
	public Response remover(@PathVariable Integer id) {
		PlanetaDomain planeta = new PlanetaDomain();
		try {
			planeta.setId(id);
			Long result = planetaResource.remover(planeta);
			return Response.builder().setSuccess().setData("regitros_apagados: " + result).build();
		} catch(Exception e) {
			return Response.builder().setError().setData("Erro ao tentar buscar o planeta informado").build();
		}
	}
	
}
