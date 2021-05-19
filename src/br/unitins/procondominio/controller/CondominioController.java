package br.unitins.procondominio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.procondominio.application.Util;
import br.unitins.procondominio.models.Condominio;
import br.unitins.procondominio.models.Endereco;
import br.unitins.procondominio.models.Telefone;
import br.unitins.procondominio.repository.CondominioRepository;

@Named
@ViewScoped
public class CondominioController extends Controller<Condominio>{
	
	private static final long serialVersionUID = 4157825206902324337L;
	private List<Condominio> listCondominio;
	
	public CondominioController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("condominio");
		setEntity((Condominio)flash.get("condominio"));
	}
	@Override
	public Condominio getEntity() {
			if(entity == null) {
				entity = new Condominio();
				entity.setTelefone(new Telefone());
				entity.setEndereco(new Endereco());
			}
		return entity;
	}
	
	public void store() {
		salvar();
		Util.addInfoMessage("Cadastrado com sucesso!");
	}
	public void edit(Condominio condominio) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("condominio", condominio);
		Util.redirect("edit.xhtml");
	}
	
	public String edit() {
		salvar();
		Util.addInfoMessage("Editado com sucesso!");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "index.xhtml?faces-redirect=true";
	}
	
	public void destroy(Condominio condominio) {
		setEntity(condominio);
		excluir();
	}
	public List<Condominio> getListCondominio() {
		CondominioRepository condominio = new CondominioRepository();
		if(listCondominio == null) {
			try {
				listCondominio = condominio.findAll();
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<Condominio>();
			}
		}
		return listCondominio;
	}
	
}
