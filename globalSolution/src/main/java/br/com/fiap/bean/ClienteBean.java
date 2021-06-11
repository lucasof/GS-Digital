package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.impl.ClienteDao;
import br.com.fiap.model.Cliente;

@Named
@RequestScoped
public class ClienteBean {
	
	private Cliente cliente = new Cliente();

	public void save() {
		new ClienteDao().save(this.cliente);
		System.out.println("Salvando..." + this.cliente);
		FacesContext.getCurrentInstance().
		addMessage(null, new FacesMessage("Cliente cadastrado com sucesso"));
		this.cliente = new Cliente();
	}
	
	public List<Cliente> getClientes(){

		return new ClienteDao().getAll();
	}
	
	public void delete(Long id) {
		new ClienteDao().delete(id);
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
