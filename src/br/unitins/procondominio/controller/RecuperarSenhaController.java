package br.unitins.procondominio.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.application.Util;
import br.unitins.procondominio.models.RecuperarSenha;
import br.unitins.procondominio.models.Usuario;
import br.unitins.procondominio.repository.RecuperarSenhaRepository;
import br.unitins.procondominio.repository.Repository;

@Named
@ViewScoped
public class RecuperarSenhaController implements Serializable {

	private static final long serialVersionUID = 3812069398253620284L;
	
	public RecuperarSenhaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("usuario");
		setUsuario((Usuario)flash.get("usuario"));
	}
	private String codigo;
	private String senha;
	private String confirmarSenha;
	private Usuario usuario;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((confirmarSenha == null) ? 0 : confirmarSenha.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecuperarSenhaController other = (RecuperarSenhaController) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (confirmarSenha == null) {
			if (other.confirmarSenha != null)
				return false;
		} else if (!confirmarSenha.equals(other.confirmarSenha))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	private boolean _confirmarSenha() {
		return getSenha().equals(getConfirmarSenha());
	}
	
	public void alterar() {
		if(!_confirmarSenha()) {
			Util.addInfoMessage("As senhas precisão ser iguais");
			return;
		}
		RecuperarSenhaRepository repo = new RecuperarSenhaRepository();
		RecuperarSenha recuperarSenha = null;
		try {
			recuperarSenha = repo.findByCodigo(getCodigo());
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		if(recuperarSenha == null) {
			Util.addErrorMessage("Codigo invalido!");
			return;
		}
		getUsuario().setSenha(this.senha);
		Repository<Usuario> repoUsuario = new Repository<Usuario>();
		try {
			repoUsuario.beginTransaction();
			repoUsuario.save(getUsuario());
			repoUsuario.commitTransaction();
			
		} catch (RepositoryException e) {
			repoUsuario.rollbackTransaction();
			return;
		}
		System.out.println(getUsuario().getSenha());
		Util.redirect("login.xhtml");
	}
	
	
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
