package br.unitins.procondominio.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.procondominio.application.JPAUtil;
import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.models.Condominio;


public class CondominioRepository extends Repository<Condominio>{
	
	public CondominioRepository() {
		super(JPAUtil.getEntityManager());
	}
	
	public CondominioRepository(EntityManager em) {
		super(em);
	}
	
	public List<Condominio> findAll() throws RepositoryException {
		EntityManager em = getEntityManager();
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" c ");
		jpql.append("FROM ");
		jpql.append(" Condominio c ");
		jpql.append("ORDER BY c.nome ");
		
		Query query = em.createQuery(jpql.toString());
		return (List<Condominio>)query.getResultList();
	}
}
