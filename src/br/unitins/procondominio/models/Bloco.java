package br.unitins.procondominio.models;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Bloco extends DefaultEntity<Bloco> {
	
	private static final long serialVersionUID = 7783700668355199707L;
	
	
	private String nome;
	private String numeroApartamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_condominio")
	private Condominio condominio;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Condominio getCondominio() {
		return condominio;
	}
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	public String getNumeroApartamento() {
		return numeroApartamento;
	}
	public void setNumeroApartamento(String numeroApartamento) {
		this.numeroApartamento = numeroApartamento;
	}
	
	
	
}
