package br.unitins.procondominio.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Condominio extends DefaultEntity<Condominio> {
	
	private static final long serialVersionUID = -6455134683343219778L;
	
	private String nome;
	private String cnpj;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_telefone" , unique = true)
	private Telefone telefone;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
	
}
