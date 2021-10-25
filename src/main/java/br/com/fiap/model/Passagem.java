package br.com.fiap.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_EAD_PASSAGEM")
public class Passagem {
	
	@Id
	@Column(name="cd_passagem")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="ds_classe", nullable = false, length = 65)
	private String classe;
	
	@Column(name = "vl_passagem", nullable = false)
	private float valor;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="cd_cliente")
	private Cliente cliente;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="cd_voo")
	private Voo voo;
	
	public Passagem() {
	}

	public Passagem(String classe, float valor, Cliente cliente) {
		this.classe = classe;
		this.valor = valor;
		this.cliente = cliente;
	}

	public Passagem(String classe, float valor, Cliente cliente, Voo voo) {
		super();
		this.classe = classe;
		this.valor = valor;
		this.cliente = cliente;
		this.voo = voo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Voo getVoo() {
		return voo;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
	}
	
	
	
	
}
