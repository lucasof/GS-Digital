package br.com.fiap.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//gerenciar unica instancia da EntityManagerFactory - SINGLETON

public class EntityManagerFactorySingleton {
	
	// 1- Atributo est�tico que armazena a �nica instancia;
	private static EntityManagerFactory fabrica;
	
	// 2 - Metodo estatico que retorna a instancia
	public static EntityManagerFactory getInstance() {
		//valida se a instancia ja existe
		if(fabrica == null) {
			fabrica = Persistence.createEntityManagerFactory("mysql");
		}
		return fabrica;
	}
	
	// 3 - construtor privado, nenhuma classe instancia 
	private EntityManagerFactorySingleton() {}
}
