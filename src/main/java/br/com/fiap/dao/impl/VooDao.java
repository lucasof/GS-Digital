package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Voo;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class VooDao{
	EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public void save(Voo voo) {
		
		manager.getTransaction().begin();
		manager.persist(voo);
		manager.getTransaction().commit();
		
		manager.close();
		
	}


	public List<Voo> getAll() {
		EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();
		String jpql = "SELECT c FROM Voo c";
		TypedQuery<Voo> createQuery = manager.createQuery(jpql, Voo.class);
		// manager.close();
		return createQuery.getResultList();
		
	}

	public void delete(Long id) {
		
		manager.getTransaction().begin();
		Voo voo = manager.find(Voo.class, id);
		manager.merge(voo);
		manager.remove(voo);
		manager.flush();
		manager.getTransaction().commit();
		
		manager.close();
	}

	public Voo findById(Long id) {
		return manager.find(Voo.class, id);		
	}


	public void update(Voo voo) {
		manager.getTransaction().begin();
		manager.merge(voo);
		manager.flush();
		manager.getTransaction().commit();		
	}


	public void delete(Voo voo) {
		manager.getTransaction().begin();
		manager.merge(voo);
		manager.remove(manager.getReference(Voo.class, voo.getId()));
		manager.flush();
		manager.getTransaction().commit();		
	}

}
