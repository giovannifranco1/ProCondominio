package br.unitins.procondominio.controller;

import java.time.LocalDateTime;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ibm.icu.text.DecimalFormat;

import br.unitins.procondominio.application.Email;
import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.application.Util;
import br.unitins.procondominio.models.RecuperarSenha;
import br.unitins.procondominio.models.Usuario;
import br.unitins.procondominio.repository.Repository;
import br.unitins.procondominio.repository.UsuarioRepository;

@Named
@ViewScoped
public class EsqueceuSenhaController extends Controller<RecuperarSenha>{

	private static final long serialVersionUID = 5266024180170629223L;
	
	private String email;
	
	@Override
	public RecuperarSenha getEntity() {
		if (entity == null) {
			entity = new RecuperarSenha();
		}
		return entity;
	}
	
	public void enviar() {
		UsuarioRepository repo = new UsuarioRepository();
		Usuario usuario = null;
		try {
			usuario = repo.findByEmail(getEmail());
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
		if (usuario == null) {
			Util.addErrorMessage("Não foi encontrado esse email no sistema.");
			return;
		}
		
		double valor1 = Math.random() * 100;
		double valor2 = Math.random() * 100;
		
		DecimalFormat format = new DecimalFormat("000");
		String codigo = format.format(valor1)+"-"+format.format(valor2);
		
		Email email = new Email(getEmail(), "Esqueceu Senha", "Informe o seguinte código: " + codigo);
		getEntity().setCodigo(codigo);
		getEntity().setUsuario(usuario);
		// gerando a data com 24 horas a mais
		LocalDateTime dataLimite = LocalDateTime.now();
		getEntity().setDataLimite(dataLimite.plusDays(1));
		getEntity().setUtilizado(false);
		
		if (salvarNovo()) {
			if (email.enviar() ) {
				Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
				flash.put("usuario", usuario);
				Util.redirect("recuperar_senha.xhtml");
			} else {
				Util.addErrorMessage("Problemas ao enviar o código por email.");
			}
		} else {
			Util.addErrorMessage("Problemas ao salvar no banco. Tente novamente mais tarde.");
		}

	}
	
	public boolean salvarNovo() {
		Repository<RecuperarSenha> repo = new Repository<RecuperarSenha>();
	
		try {
			repo.beginTransaction();
			repo.save(getEntity());
			repo.commitTransaction();
			
			limpar();
			return true;
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			return false;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
