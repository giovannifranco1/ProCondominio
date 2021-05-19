package br.unitins.procondominio.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.procondominio.application.JPAUtil;
import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.models.Estado;

public class EstadoRepository extends Repository<Estado>{
	
	public EstadoRepository() {
		super(JPAUtil.getEntityManager());
	}
	
	public EstadoRepository(EntityManager em) {
		super(em);
	}
	
	public List<Estado> findAll() throws RepositoryException {
		EntityManager em = getEntityManager();
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" u ");
		jpql.append("FROM ");
		jpql.append(" Estado u ");
		jpql.append("ORDER BY u.nome ");
		
		Query query = em.createQuery(jpql.toString());
		return query.getResultList();
	}
}
