package br.unitins.procondominio.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.procondominio.application.Util;
import br.unitins.procondominio.models.Estado;
import br.unitins.procondominio.repository.EstadoRepository;

@Named
@ViewScoped
public class EstadoController extends Controller<Estado> {
	
	private static final long serialVersionUID = 295059785868553564L;
	private List<Estado> estadoList;
	
	public EstadoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("estado");
		setEntity((Estado)flash.get("estado"));
	}
	
	@Override
	public Estado getEntity() {
		if(entity == null) {
			entity = new Estado();
		}
		return entity;
	}
	
	public void edit(Estado estado) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("estado", estado);
		Util.redirect("edit.xhtml");
	}
	public void destroy(Estado estado) {
		setEntity(estado);
		excluir();
	}
	public String edit() {
		salvar();
		Util.addInfoMessage("Editado com sucesso!");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "index.xhtml?faces-redirect=true";
	}
	
	public void store() {
		salvar();
		Util.addInfoMessage("Cadastro realizado com sucesso!");
	}
	public List<Estado> getEstadoList() {
		EstadoRepository jpa = new EstadoRepository();
		if(estadoList == null) {
			try {
				estadoList = jpa.findAll();
			} catch (Exception e) {
			
			}
		}
		return estadoList;
	}
	public List<Estado> filtrarEstados(String nome){
		String filtro = nome.toLowerCase();
		return getEstadoList().stream().filter(e ->{
			String nomeEstado = e.getNome().toLowerCase();
			return nomeEstado.startsWith(filtro);
		}).collect(Collectors.toList());
	}

}
