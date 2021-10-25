package br.com.fiap.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="T_EAD_CLIENTE")
public class Cliente {
	
	@Id
	@Column(name="cd_cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nm_cliente", nullable = false, length = 150)
	private String nome;
	
	@Column(name = "nr_documento", nullable = false, length = 45)
	private String documento;

	@Column(name="nm_paisOrigem", nullable = false, length = 85)
	private String paisOrigem;
	
	@Column(name="ds_email", nullable = false, length = 150)
	private String email;
	
	@OneToMany(mappedBy = "cliente")
	private List<Passagem> passagens;

	public void addPassagem(Passagem passagem) {	
		if(passagens == null)
			passagens = new ArrayList<>();
		passagens.add(passagem);
		passagem.setCliente(this);	
	}
	
	public Cliente() {
	}


	public Cliente(String nome, String documento, String paisOrigem, String email) {
		this.nome = nome;
		this.documento = documento;
		this.paisOrigem = paisOrigem;
		this.email = email;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public String getPaisOrigem() {
		return paisOrigem;
	}


	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Passagem> getPassagens() {
		return passagens;
	}


	public void setPassagens(List<Passagem> passagens) {
		this.passagens = passagens;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", documento=" + documento + ", paisOrigem=" + paisOrigem + ", email=" + email
				+ ", passagens=" + passagens + "]";
	}
	
	
	
	
}
