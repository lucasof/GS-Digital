package br.com.fiap.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="T_EAD_AERONAVE")
public class Aeronave {
	
	@Id
	@Column(name="cd_aeronave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="ds_marca", nullable = false, length = 120)
	private String marca;
	
	@Column(name="ds_modelo", nullable = false, length = 120)
	private String modelo;
	
	@Column(name = "nr_capacidade", nullable = false)
	private Long capacidade;
	
	@Column(name = "ds_tipo", nullable = false,length = 25)
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="cd_ciaAerea")
	private CiaAerea ciaAerea;
	
	@OneToMany(mappedBy = "aeronave", cascade = CascadeType.PERSIST)
	private List<Voo> voos;
	
	public Aeronave() {
	}
	
	public Aeronave(String marca, String modelo, Long capacidade, Tipo tipo, CiaAerea ciaAerea) {
		this.marca = marca;
		this.modelo = modelo;
		this.capacidade = capacidade;
		this.tipo = tipo;
		this.ciaAerea = ciaAerea;
	}

	public void addVoo(Voo voo) {	
		if(voo == null)
			voos = new ArrayList<Voo>();
		voos.add(voo);
		voo.setAeronave(this);	
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Long getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Long capacidade) {
		this.capacidade = capacidade;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public CiaAerea getCiaAerea() {
		return ciaAerea;
	}

	public void setCiaAerea(CiaAerea ciaAerea) {
		this.ciaAerea = ciaAerea;
	}

	
	
}
