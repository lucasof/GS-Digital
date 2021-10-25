package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Cliente;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class ClienteDao {

	EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public void save(Cliente cliente) {
		
		manager.getTransaction().begin();
		manager.persist(cliente);
		manager.getTransaction().commit();
		
		manager.close();
		
	}


	public List<Cliente> getAll() {
		EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();
		String jpql = "SELECT c FROM Cliente c";
		TypedQuery<Cliente> createQuery = manager.createQuery(jpql, Cliente.class);
		// manager.close();
		return createQuery.getResultList();
		
	}

	public void delete(Long id) {
		
		manager.getTransaction().begin();
		Cliente cliente = manager.find(Cliente.class, id);
		manager.merge(cliente);
		manager.remove(cliente);
		manager.flush();
		manager.getTransaction().commit();
		
		manager.close();
	}

	public Cliente findById(Long id) {
		return manager.find(Cliente.class, id);		
	}


	public void update(Cliente cliente) {
		manager.getTransaction().begin();
		manager.merge(cliente);
		manager.flush();
		manager.getTransaction().commit();		
	}


	public void delete(Cliente cliente) {
		manager.getTransaction().begin();
		manager.merge(cliente);
		manager.remove(manager.getReference(Cliente.class, cliente.getId()));
		manager.flush();
		manager.getTransaction().commit();		
	}




}
