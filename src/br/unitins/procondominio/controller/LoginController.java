package br.unitins.procondominio.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.procondominio.application.Util;
import br.unitins.procondominio.models.Usuario;
import br.unitins.procondominio.repository.UsuarioRepository;

@Named
@RequestScoped
public class LoginController extends Controller<Usuario> {

	private static final long serialVersionUID = 5207406133852710353L;
	private List<Usuario> listUsuario; 

	@Override
	public Usuario getEntity() {
		if(entity == null) {
			entity = new Usuario();
		}
		return entity;
	}
	
	public void logar() {
		try {
			UsuarioRepository usuario = new UsuarioRepository();
			listUsuario = usuario.findAll();
			
			for(Usuario user: listUsuario) {
				if(getEntity().getLogin().equals(user.getLogin()) && getEntity().getSenha().equals(user.getSenha())){
					Util.redirect("admin/condominio/index.xhtml");
					return;
				}
			}
			Util.addErrorMessage("Login ou senha Invalida!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
