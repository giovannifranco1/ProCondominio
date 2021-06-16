package br.unitins.procondominio.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.hibernate.type.LocalDateType;

import br.unitins.procondominio.application.Util;
import br.unitins.procondominio.models.Morador;
import br.unitins.procondominio.models.Telefone;
import br.unitins.procondominio.repository.BlocoRepository;
import br.unitins.procondominio.repository.MoradorRepository;

@Named
@ViewScoped
public class MoradorController extends Controller<Morador> {

	private static final long serialVersionUID = -2846078466413986512L;
	private List<Morador> listMorador;
	
	public MoradorController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("morador");
		setEntity((Morador)flash.get("morador"));
	}
	@Override
	public Morador getEntity() {
			if(entity == null) {
				entity = new Morador();
				entity.setTelefone(new Telefone());
			}
		return entity;
	}
	
	public void store() {
		salvar();
		Util.addInfoMessage("Cadastrado com sucesso!");
	}
	public void edit(Morador morador) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("morador", morador);
		Util.redirect("edit.xhtml");
	}
	
	public String edit() {
		salvar();
		Util.addInfoMessage("Editado com sucesso!");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "index.xhtml?faces-redirect=true";
	}
	
	public void destroy(Morador morador) {
		setEntity(morador);
		excluir();
	}
	public void teste() {
		System.out.println("teste");
	}
//	public List<Integer> numeroApartamento() {
//		BlocoRepository repo = new BlocoRepository();
//		try {
//			numeroApartamento =  repo.findByid(getEntity().getBloco().getNumeroApartamento());
//		} catch (RepositoryException e) {
//			numeroApartamento =  new ArrayList<Municipio>();
//		}
//		return ;
//		
//	}
	public List<Morador> getListMorador() {
		MoradorRepository morador = new MoradorRepository();
		if(listMorador == null) {
			try {
				listMorador = morador.findAll();
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<Morador>();
			}
		}
		return listMorador;
	}
}
