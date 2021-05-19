package br.unitins.procondominio.models;


import javax.persistence.Entity;


@Entity
public class Telefone extends DefaultEntity<Telefone> {
	
	public Telefone() {}
	public Telefone(String codigoPostal, String numero) {
		this.codigoPostal = codigoPostal;
		this.numero = numero;
	}

	private static final long serialVersionUID = 6416047741352806036L;

	private String codigoPostal;
	private String numero;
	
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
