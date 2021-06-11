package br.com.fiap.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="T_EAD_CIA_AEREA")
@SequenceGenerator(sequenceName = "SQ_CIA_AEREA", name="ciaAerea", allocationSize = 1)
public class CiaAerea {
	
	@Id
	@Column(name="cd_ciaAerea")
	@GeneratedValue(generator = "ciaAerea", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="nm_ciaAerea", nullable = false, length = 80)
	private String nome;
	
	@Column(name="nr_telefone", nullable = false, length = 15)
	private Long telefone;
	
	@Column(name="ds_email", nullable = false, length= 150)
	private String email;
	
	@Column(name="nm_paisOrigem", length=80)
	private String paisOrigem;

	@OneToMany(mappedBy = "ciaAerea", cascade = CascadeType.PERSIST)
	private List<Aeronave> aeronaves;
	

	public CiaAerea() {
	}
	
	public CiaAerea(String nome, Long telefone, String email, String paisOrigem) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.paisOrigem = paisOrigem;
	}

	public void addAeronave(Aeronave aeronave) {	
		if(aeronaves == null)
			aeronaves = new ArrayList<>();
		aeronaves.add(aeronave);
		aeronave.setCiaAerea(this);	
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

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}
	public List<Aeronave> getAeronaves() {
		return aeronaves;
	}
	public void setAeronaves(List<Aeronave> aeronaves) {
		this.aeronaves = aeronaves;
	}
	
	
	
}
