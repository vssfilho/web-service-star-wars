package com.b2wdigital.starwars.rest.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de domain do planeta */
public class PlanetaDomain {

	private Integer id;
	private String nome;
	private List<String> clima;
	private List<String> terreno;
	private Integer numeroDeFilmes;
	
	public PlanetaDomain() {
		this.clima = new ArrayList<>();
		this.terreno = new ArrayList<>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<String> getClima() {
		return clima;
	}
	
	public void setClima(List<String> clima) {
		this.clima = clima;
	}
	
	public List<String> getTerreno() {
		return terreno;
	}
	
	public void setTerreno(List<String> terreno) {
		this.terreno = terreno;
	}
	
	public Integer getNumeroDeFilmes() {
		return numeroDeFilmes;
	}
	
	public void setNumeroDeFilmes(Integer numeroDeFilmes) {
		this.numeroDeFilmes = numeroDeFilmes;
	}
	
	public void addClima(String clima) {
		this.clima.add(clima);
	}
	
	public void addTerreno(String terreno) {
		this.terreno.add(terreno);
	}
	
}
