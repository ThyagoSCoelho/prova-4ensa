package com.livraria.entity;

import java.util.ArrayList;
import java.util.Objects;

public class Genero {

	public Integer id;
	public String titulo;

	public Genero(){

	}

	public Genero(int id, String titulo){
		this.id = id;
		this.titulo = titulo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Genero genero = (Genero) o;
		return Objects.equals(titulo, genero.titulo);
	}

	public static int gerarIdGenero(ArrayList<Genero> generos){
		int Id = 0;
		if (generos.isEmpty()) {
			return Id;
		} else {
			for(Genero genero: generos) {
				if (genero.id == Id) {
					++Id;
				}
			}
			return Id;
		}
	}

	@Override
	public String toString() {
		return titulo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValor() {
		return titulo;
	}
	
	public void setValor(String titulo) {
		this.titulo = titulo;
	}
}
