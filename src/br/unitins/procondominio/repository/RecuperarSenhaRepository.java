package br.unitins.procondominio.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.procondominio.application.JPAUtil;
import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.models.RecuperarSenha;

public class RecuperarSenhaRepository extends Repository<RecuperarSenha>{
	public RecuperarSenhaRepository() {
		super(JPAUtil.getEntityManager());
	}
	public RecuperarSenhaRepository(EntityManager em) {
		super(em);
	}
	public RecuperarSenha findByCodigo(String codigo) throws RepositoryException {
		EntityManager em = getEntityManager();
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" u ");
		jpql.append("FROM ");
		jpql.append(" RecuperarSenha u ");
		jpql.append("WHERE ");
		jpql.append(" UPPER(u.codigo) LIKE UPPER(:codigo)");
		
		Query query = em.createQuery(jpql.toString());
		query.setParameter("codigo", codigo);
		
		RecuperarSenha recuperarSenha = null;
		try {
			recuperarSenha = (RecuperarSenha) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return recuperarSenha;
	}

}
