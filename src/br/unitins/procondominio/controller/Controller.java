package br.unitins.procondominio.controller;

import java.io.Serializable;

import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.application.Util;
import br.unitins.procondominio.models.DefaultEntity;
import br.unitins.procondominio.repository.Repository;

public abstract class Controller<T extends DefaultEntity<T>> implements Serializable {
	
	private static final long serialVersionUID = -7172376339410561672L;
	
	protected T entity;

	public Controller() {
		super();
	}
	
	public abstract T getEntity();
	
	public void setEntity(T entity) {
		this.entity = entity;
	}
	

	public void salvar() {
		Repository<T> repo = new Repository<T>();
	
		try {
			repo.beginTransaction();
			repo.save(getEntity());
			repo.commitTransaction();
			
			limpar();
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
		}
	}

	public void excluir() {
		Repository<T> repo = new Repository<T>();
		try {
			repo.beginTransaction();
			repo.remove(getEntity());
			repo.commitTransaction();
			
			limpar();
			Util.addInfoMessage("removido com sucesso.");
		} catch (RepositoryException e) {
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void limpar() {
		entity = null;
	}

	public void editar(T entity) {
		System.out.println("Entrou no editar");
		setEntity(entity);
	}
}