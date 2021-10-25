package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.CiaAerea;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class CiaAereaDao{

	EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public void save(CiaAerea ciaAerea) {
		
		manager.getTransaction().begin();
		manager.persist(ciaAerea);
		manager.getTransaction().commit();
		
		manager.close();
		
	}


	public List<CiaAerea> getAll() {
		EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();
		String jpql = "SELECT c FROM CiaAerea c";
		TypedQuery<CiaAerea> createQuery = manager.createQuery(jpql, CiaAerea.class);
		// manager.close();
		return createQuery.getResultList();
		
	}

	public void delete(Long id) {
		
		manager.getTransaction().begin();
		CiaAerea ciaAerea = manager.find(CiaAerea.class, id);
		manager.merge(ciaAerea);
		manager.remove(ciaAerea);
		manager.flush();
		manager.getTransaction().commit();
		
		manager.close();
	}

	public CiaAerea findById(Long id) {
		return manager.find(CiaAerea.class, id);		
	}


	public void update(CiaAerea ciaAerea) {
		manager.getTransaction().begin();
		manager.merge(ciaAerea);
		manager.flush();
		manager.getTransaction().commit();		
	}


	public void delete(CiaAerea ciaAerea) {
		manager.getTransaction().begin();
		manager.merge(ciaAerea);
		manager.remove(manager.getReference(CiaAerea.class, ciaAerea.getId()));
		manager.flush();
		manager.getTransaction().commit();		
	}

	
}
