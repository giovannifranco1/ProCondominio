package br.unitins.procondominio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.procondominio.application.Util;
import br.unitins.procondominio.models.TipoUsuario;
import br.unitins.procondominio.models.Usuario;
import br.unitins.procondominio.repository.UsuarioRepository;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> {

	private static final long serialVersionUID = 167097623396529413L;
	private List<Usuario> listUsuario;
	
	public UsuarioController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("usuario");
		setEntity((Usuario)flash.get("usuario"));
	}
	@Override
	public Usuario getEntity() {
			if(entity == null) {
				entity = new Usuario();
			}
		return entity;
	}
	public void destroy(Usuario usuario) {
		setEntity(usuario);
		excluir();
	}
	public void store() {
		salvar();
		Util.addInfoMessage("Cadastrado com sucesso!");
	}
	
	public void edit(Usuario usuario) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuario", usuario);
		Util.redirect("edit.xhtml");
	}
	
	public String edit() {
		salvar();
		Util.addInfoMessage("Editado com sucesso!");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "index.xhtml?faces-redirect=true";
	}
	
	public List<Usuario> getListUsuario() {
		if(listUsuario == null) {
			try {
				UsuarioRepository usuario = new UsuarioRepository();
				listUsuario = usuario.findAll();
			} catch (Exception e) {
				new ArrayList<Usuario>();
			}
		}
		return listUsuario;
	}

	public void setListUsuario(List<Usuario> listUsuario) {
		this.listUsuario = listUsuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TipoUsuario[] getListaPerfil() {
		return TipoUsuario.values();
	}
}
