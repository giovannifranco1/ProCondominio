package br.unitins.procondominio.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.procondominio.application.Util;
import br.unitins.procondominio.models.Bloco;
import br.unitins.procondominio.models.Condominio;

import br.unitins.procondominio.repository.BlocoRepository;
import br.unitins.procondominio.repository.CondominioRepository;

@Named
@ViewScoped
public class BlocoController extends Controller<Bloco> {

	private static final long serialVersionUID = 2372300847911090357L;
	private List<Condominio> condominioList;
	private Bloco bloco;
	
	

	private List<Bloco> blocoList;
	
	public BlocoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("bloco");
		setEntity((Bloco)flash.get("bloco"));
	}
	public List<Bloco> getBlocoList() {
		BlocoRepository jpa = new BlocoRepository();
		if(blocoList == null) {
			try {
				blocoList = jpa.findAll();
			} catch (Exception e) {
			
			}
		}
		return blocoList;
	}
	public void teste() {
		System.out.println("teste");
	}
	public void setBlocoList(List<Bloco> blocoList) {
		this.blocoList = blocoList;
	}

	@Override
	public Bloco getEntity() {
		if(entity == null) {
			entity = new Bloco();
		}
		return entity;
	}
	
	public void destroy(Bloco bloco) {
		setEntity(bloco);
		excluir();
	}
	public void store() {
		salvar();
		Util.addInfoMessage("Cadastrado com sucesso!");
	}
	
	
	public void edit(Bloco bloco) {
		System.out.println("ok");
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("bloco", bloco);
		Util.redirect("edit.xhtml");
	}
	
	public String edit() {
		salvar();
		Util.addInfoMessage("Editado com sucesso!");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "index.xhtml?faces-redirect=true";
	}
	
	
	public List<Condominio> filtrarCondominios(String nome){
		String filtro = nome.toLowerCase();
		return getCondominioList().stream().filter(e ->{
			String nomeCondominio = e.getNome().toLowerCase();
			return nomeCondominio.startsWith(filtro);
		}).collect(Collectors.toList());
	}
	
//	public List<Bloco> filtrarBlocos(String nome){
//		String filtro = nome.toLowerCase();
//		return getBlocoList().stream().filter(e ->{
//			String nomeBloco = e.getNome().toLowerCase();
//			return nomeBloco.startsWith(filtro);
//		}).collect(Collectors.toList());
//	}
	
	
	
	public List<Condominio> getCondominioList() {
		CondominioRepository jpa = new CondominioRepository();
		if(condominioList == null) {
			try {
				condominioList = jpa.findAll();
			} catch (Exception e) {
			
			}
		}
		return condominioList;
	}

	public Bloco getBloco() {
		return bloco;
	}
	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}
	
	public void setCondominioList(List<Condominio> condominioList) {
		this.condominioList = condominioList;
	}
}
