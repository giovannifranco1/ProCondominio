package br.unitins.procondominio.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.procondominio.application.Email;
import br.unitins.procondominio.application.Util;
import br.unitins.procondominio.models.FaleConosco;

@Named
@ViewScoped
public class FaleConoscoController implements Serializable{
	private static final long serialVersionUID = -9013115008436640302L;
	
	private FaleConosco faleConosco = new FaleConosco();

	
	public FaleConosco getFaleConosco() {
		return faleConosco;
	}


	public void setFaleConosco(FaleConosco faleConosco) {
		this.faleConosco = faleConosco;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void enviar() {
		System.out.println(faleConosco.getAssunto());
		try {
			Email email = new Email("developtopicos@gmail.com", faleConosco.getAssunto(), "Cliente: " + faleConosco.getNome()+ "\n" 
				+ "Telefone: " + faleConosco.getTelefone() + "\n" + "Email: " + faleConosco.getEmail() + "\n" + faleConosco.getMensagem());
			Email emailCliente = new Email(faleConosco.getEmail(), "Fale Conosco", "Olá " + faleConosco.getNome() + ", seu Email foi enviado com sucesso. Aguarde que logo entraremos em contato.");
			if(email.enviar() && emailCliente.enviar()) {
				faleConosco = null;
				Util.addInfoMessage("Enviado com sucesso!");
			}
		} catch (Exception e) {
			Util.addErrorMessage("Erro ao enviar");
			return;
		}
		
	}
	
}
