package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Aeronave;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class AeronaveDao{

	EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public void save(Aeronave aeronave) {
		
		manager.getTransaction().begin();
		manager.persist(aeronave);
		manager.getTransaction().commit();
		
		manager.close();
		
	}


	public List<Aeronave> getAll() {
		EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();
		String jpql = "SELECT c FROM Aeronave c";
		TypedQuery<Aeronave> createQuery = manager.createQuery(jpql, Aeronave.class);
		// manager.close();
		return createQuery.getResultList();
		
	}

	public void delete(Long id) {
		
		manager.getTransaction().begin();
		Aeronave aeronave = manager.find(Aeronave.class, id);
		manager.merge(aeronave);
		manager.remove(aeronave);
		manager.flush();
		manager.getTransaction().commit();
		
		manager.close();
	}

	public Aeronave findById(Long id) {
		return manager.find(Aeronave.class, id);		
	}


	public void update(Aeronave aeronave) {
		manager.getTransaction().begin();
		manager.merge(aeronave);
		manager.flush();
		manager.getTransaction().commit();		
	}


	public void delete(Aeronave aeronave) {
		manager.getTransaction().begin();
		manager.merge(aeronave);
		manager.remove(manager.getReference(Aeronave.class, aeronave.getId()));
		manager.flush();
		manager.getTransaction().commit();		
	}


}
