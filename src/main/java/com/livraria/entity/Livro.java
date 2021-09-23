package com.livraria.entity;

import java.util.ArrayList;

public class Livro{

	public Integer id;
	public String titulo;
	public String autor;
	public String genero;

	public Livro(){

	}
	
	public Livro (int id,String titulo, String autor, String genero) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
	}

	public static int gerarIdLivro(ArrayList<Livro> livros){
		int Id = 0;
		if (livros.isEmpty()) {
			return Id;
		} else {
			for(Livro livro: livros) {
				if (livro.id == Id) {
					++Id;
				}
			}
			return Id;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
