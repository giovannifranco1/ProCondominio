package br.unitins.procondominio.repository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.unitins.procondominio.application.JPAUtil;
import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.models.DefaultEntity;

public class Repository<T extends DefaultEntity<T>> {
	
	protected EntityManager entityManager = null;

	public Repository(EntityManager em) {
		super();
		entityManager = em;
	}
	
	public Repository() {
		super();
		entityManager = JPAUtil.getEntityManager();
	}

	public void beginTransaction() throws RepositoryException {
		try {
			getEntityManager().getTransaction().begin();
		} catch (Exception e) {
			System.out.println("Problemas ao iniciar uma transação.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao iniciar o acesso ao banco de dados.");
		}
	}

	public void commitTransaction() throws RepositoryException {
		try {
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Problemas ao concluir a transação (Commit).");
			e.printStackTrace();
			throw new RepositoryException("Erro ao concluir a operação no banco de dados.");
		}
	}

	public void rollbackTransaction() {
		try {
			getEntityManager().getTransaction().rollback();
		} catch (Exception e) {
			System.out.println("Problemas ao concluir uma transação (Rollback).");
			e.printStackTrace();
		}
	}

	public void save(T t) throws RepositoryException {
		try {
			EntityManager em = getEntityManager();
			em.merge(t);
			
		} catch (Exception e) {
			System.out.println("Erro ao executar o método Save do Repository");
			throw new RepositoryException("Erro ao Salvar");
		}
		
	}

	public void remove(T t) throws RepositoryException {
		try {
			EntityManager em = getEntityManager();
			T obj = em.merge(t);
			em.remove(obj);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o método Remove do Repository");
			throw new RepositoryException("Erro ao Remover");
		}
	}

	public T findById(int id) throws RepositoryException {
		try {
			// obtendo o tipo da classe de forma generica (a classe deve ser publica)
			final ParameterizedType type = 	(ParameterizedType) getClass().getGenericSuperclass();
			@SuppressWarnings("unchecked")
			Class<T> tClass = (Class<T>) (type).getActualTypeArguments()[0];
			
			T t = (T) getEntityManager().find(tClass, id);
			return t;
		} catch (Exception e) {
			System.out.println("Erro ao executar o método find do Repository");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os dados");
		}
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
