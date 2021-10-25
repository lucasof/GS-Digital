package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fiap.model.Voo;
@Named //CDI
@RequestScoped
public class VooBean {
	
	private Voo voo = new Voo();
	/*
	 * public void save() { new VooDaoImpl().create(this.voo);
	 * System.out.println("Salvando..." + this.voo); }
	 * 
	 * public List<Voo> getVoos(){ return new VooDaoImpl().getAll(); }
	 */
	
}
