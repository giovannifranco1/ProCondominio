package br.unitins.procondominio.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.procondominio.application.JPAUtil;
import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.models.Bloco;
import br.unitins.procondominio.models.RecuperarSenha;

public class BlocoRepository extends Repository<Bloco> {
	
	public BlocoRepository() {
		super(JPAUtil.getEntityManager());
	}
	
	public BlocoRepository(EntityManager em) {
		super(em);
	}
	
	public List<Bloco> findAll() throws RepositoryException {
		EntityManager em = getEntityManager();
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" b ");
		jpql.append("FROM ");
		jpql.append(" Bloco b ");
		jpql.append("ORDER BY b.nome ");
		
		Query query = em.createQuery(jpql.toString());
		return (List<Bloco>)query.getResultList();
	}
	public Bloco findById(Integer id) throws RepositoryException {
		EntityManager em = getEntityManager();
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" u ");
		jpql.append("FROM ");
		jpql.append(" Bloco u ");
		jpql.append("WHERE ");
		jpql.append(" u.id = :id ");
		
		Query query = em.createQuery(jpql.toString());
		query.setParameter("codigo", id);
		
		Bloco bloco = null;
		try {
			bloco = (Bloco) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bloco;
	}
}
