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
@Table(name="T_EAD_DESTINO")
public class Destino {
	
	@Id
	@Column(name="cd_destino")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nm_pais", nullable = false, length = 80)
	private String pais;
	
	@Column(name="nm_cidade", nullable = false, length = 80)
	private String cidade;
	
	@Column(name="nm_aeroporto", nullable = false, length = 120)
	private String aeroporto;
	
	@Column(name = "nr_voosDia", nullable = false)
	private Long voosDia;
	
	@OneToMany(mappedBy = "destino")
	private List<Voo> voos;
	
	public void addVoo(Voo voo) {	
		if(voo == null)
			voos = new ArrayList<Voo>();
		voos.add(voo);
		voo.setDestino(this);	
	}

	public Destino() {

	}
	public Destino(String pais, String cidade, String aeroporto, Long voosDia) {
		super();
		this.pais = pais;
		this.cidade = cidade;
		this.aeroporto = aeroporto;
		this.voosDia = voosDia;
	}
	
	public Destino(String pais, String cidade, String aeroporto, Long voosDia, List<Voo> voos) {
		super();
		this.pais = pais;
		this.cidade = cidade;
		this.aeroporto = aeroporto;
		this.voosDia = voosDia;
		this.voos = voos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getAeroporto() {
		return aeroporto;
	}

	public void setAeroporto(String aeroporto) {
		this.aeroporto = aeroporto;
	}

	public Long getVoosDia() {
		return voosDia;
	}

	public void setVoosDia(Long voosDia) {
		this.voosDia = voosDia;
	}

	public List<Voo> getVoos() {
		return voos;
	}

	public void setVoos(List<Voo> voos) {
		this.voos = voos;
	}
	
	
}
