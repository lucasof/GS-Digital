package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.mysql.cj.x.protobuf.Mysqlx.Error.Severity;

import br.com.fiap.dao.impl.UsuarioDao;
import br.com.fiap.model.Usuario;

//mais atual que o @managed do JSF
@Named //CDI
//definir o escopo do bean, o padrao é view
@RequestScoped
public class UsuarioBean {
	
	private Usuario usuario= new Usuario();
	
	public void save() {
		new UsuarioDao().save(this.usuario);
		System.out.println("Salvando..." + this.usuario);
		//retorna mensagem de sucesso
		FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage("Usuario cadastrado com sucesso"));
	}
	
	public List<Usuario> getUsuarios(){
		return new UsuarioDao().getAll();
	}

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		boolean exist = new UsuarioDao().exist(usuario);
		if(exist) {
			context.getExternalContext().getSessionMap().put("usuario", usuario);
			return "home?faces-redirect=true";			
			
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);

		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario ou senha inválido!", ""));

		return "login?faces-redirect=true";			

	}
	public  Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
