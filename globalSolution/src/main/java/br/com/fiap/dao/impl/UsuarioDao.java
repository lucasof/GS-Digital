package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Usuario;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class UsuarioDao {
	EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public void save(Usuario usuario) {

		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();
		
		manager.close();		
	}

	public List<Usuario> getAll() {
		String jpql = "SELECT u FROM Usuario u";
		TypedQuery<Usuario> createQuery = manager.createQuery(jpql, Usuario.class);
		// manager.close();
		return createQuery.getResultList();
		
	}
	
	public boolean exist(Usuario user) {
		TypedQuery<Usuario> query = manager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email "
														+ "AND u.senha = :senha", Usuario.class);
		query.setParameter("email", user.getEmail());
		query.setParameter("senha", user.getSenha());
		
		try { 
			query.getSingleResult();
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}
	
	public void delete(Long id) {
		
		manager.getTransaction().begin();
		Usuario usuario = manager.find(Usuario.class, id);
		manager.merge(usuario);
		manager.remove(usuario);
		manager.flush();
		manager.getTransaction().commit();
		
		manager.close();
	}

	public Usuario findById(Long id) {
		return manager.find(Usuario.class, id);		
	}


	public void update(Usuario usuario) {
		manager.getTransaction().begin();
		manager.merge(usuario);
		manager.flush();
		manager.getTransaction().commit();		
	}


	public void delete(Usuario usuario) {
		manager.getTransaction().begin();
		manager.merge(usuario);
		manager.remove(manager.getReference(Usuario.class, usuario.getId()));
		manager.flush();
		manager.getTransaction().commit();		
	}
	
}
