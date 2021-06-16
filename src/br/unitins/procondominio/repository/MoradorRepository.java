package br.unitins.procondominio.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.procondominio.application.JPAUtil;
import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.models.Morador;

public class MoradorRepository extends Repository<Morador> {
	
	public MoradorRepository() {
		super(JPAUtil.getEntityManager());
	}
	
	public MoradorRepository(EntityManager em) {
		super(em);
	}
	
	public List<Morador> findAll() throws RepositoryException {
		EntityManager em = getEntityManager();
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" b ");
		jpql.append("FROM ");
		jpql.append(" Bloco b ");
		jpql.append("ORDER BY b.nome ");
		
		Query query = em.createQuery(jpql.toString());
		return (List<Morador>)query.getResultList();
	}
}
