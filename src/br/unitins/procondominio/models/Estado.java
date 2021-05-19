package br.unitins.procondominio.models;

import javax.persistence.Entity;

@Entity
public class Estado extends DefaultEntity<Estado> {

	private static final long serialVersionUID = -3543978356738486230L;

	private String nome;
	private String sigla;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}