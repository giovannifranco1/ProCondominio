package br.unitins.procondominio.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.procondominio.application.JPAUtil;
import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.models.Usuario;

public class UsuarioRepository extends Repository<Usuario>{
	
	public UsuarioRepository() {
		super(JPAUtil.getEntityManager());
	}
	
	public UsuarioRepository(EntityManager em) {
		super(em);
	}
	public Usuario findByEmail(String email) throws RepositoryException {
		EntityManager em = getEntityManager();
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" u ");
		jpql.append("FROM ");
		jpql.append(" Usuario u ");
		jpql.append("WHERE ");
		jpql.append(" u.login = :email ");
		
		Query query = em.createQuery(jpql.toString());
		query.setParameter("email", email);
		
		Usuario usuario = null;
		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	public List<Usuario> findAll() throws RepositoryException {
		EntityManager em = getEntityManager();
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" u ");
		jpql.append("FROM ");
		jpql.append(" Usuario u ");
		jpql.append("ORDER BY u.login");
		
		Query query = em.createQuery(jpql.toString());
		return query.getResultList();
	}
}
