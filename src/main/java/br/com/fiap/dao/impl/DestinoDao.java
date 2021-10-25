package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Destino;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class DestinoDao {

	EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public void save(Destino destino) {
		
		manager.getTransaction().begin();
		manager.persist(destino);
		manager.getTransaction().commit();
		
		manager.close();
		
	}


	public List<Destino> getAll() {
		EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();
		String jpql = "SELECT c FROM Destino c";
		TypedQuery<Destino> createQuery = manager.createQuery(jpql, Destino.class);
		// manager.close();
		return createQuery.getResultList();
		
	}

	public void delete(Long id) {
		
		manager.getTransaction().begin();
		Destino destino = manager.find(Destino.class, id);
		manager.merge(destino);
		manager.remove(destino);
		manager.flush();
		manager.getTransaction().commit();
		
		manager.close();
	}

	public Destino findById(Long id) {
		return manager.find(Destino.class, id);		
	}


	public void update(Destino destino) {
		manager.getTransaction().begin();
		manager.merge(destino);
		manager.flush();
		manager.getTransaction().commit();		
	}


	public void delete(Destino destino) {
		manager.getTransaction().begin();
		manager.merge(destino);
		manager.remove(manager.getReference(Destino.class, destino.getId()));
		manager.flush();
		manager.getTransaction().commit();		
	}

}
