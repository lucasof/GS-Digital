package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Passagem;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class PassagemDao{

	EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public void save(Passagem passagem) {
		
		manager.getTransaction().begin();
		manager.persist(passagem);
		manager.getTransaction().commit();
		
		manager.close();
		
	}


	public List<Passagem> getAll() {
		EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();
		String jpql = "SELECT c FROM Passagem c";
		TypedQuery<Passagem> createQuery = manager.createQuery(jpql, Passagem.class);
		// manager.close();
		return createQuery.getResultList();
		
	}

	public void delete(Long id) {
		
		manager.getTransaction().begin();
		Passagem passagem = manager.find(Passagem.class, id);
		manager.merge(passagem);
		manager.remove(passagem);
		manager.flush();
		manager.getTransaction().commit();
		
		manager.close();
	}

	public Passagem findById(Long id) {
		return manager.find(Passagem.class, id);		
	}


	public void update(Passagem passagem) {
		manager.getTransaction().begin();
		manager.merge(passagem);
		manager.flush();
		manager.getTransaction().commit();		
	}


	public void delete(Passagem passagem) {
		manager.getTransaction().begin();
		manager.merge(passagem);
		manager.remove(manager.getReference(Passagem.class, passagem.getId()));
		manager.flush();
		manager.getTransaction().commit();		
	}


}


