package br.unitins.procondominio.models;

import javax.persistence.Column;
import javax.persistence.Entity;



@Entity
public class Usuario extends DefaultEntity<Usuario> {
	
	private static final long serialVersionUID = -8688715946444057926L;
	private String login;
	private String senha;
	
	@Column(name = "tipo_usuario")
	private TipoUsuario tipoUsuario;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
