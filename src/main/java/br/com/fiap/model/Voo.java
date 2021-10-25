package br.com.fiap.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_EAD_VOO")
public class Voo {
	
	@Id
	@Column(name="cd_voo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_saido", nullable = false)
	private Calendar dataSaida;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_chegada", nullable = false)
	private Calendar dataChegada;
	
	@OneToMany(mappedBy = "voo")
	private List<Passagem> passagens;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="cd_destino")
	private Destino destino;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="cd_aeronave")
	private Aeronave aeronave;
	
	
	
	public Voo() {
	}

	public Voo(Calendar dataSaida, Calendar dataChegada) {
		this.dataSaida = dataSaida;
		this.dataChegada = dataChegada;
	}

	
	public Voo(Calendar dataSaida, Calendar dataChegada, Destino destino, Aeronave aeronave) {
		super();
		this.dataSaida = dataSaida;
		this.dataChegada = dataChegada;
		this.destino = destino;
		this.aeronave = aeronave;
	}
	
	public Voo(Calendar dataSaida, Calendar dataChegada, List<Passagem> passagens, Destino destino, Aeronave aeronave) {
		super();
		this.dataSaida = dataSaida;
		this.dataChegada = dataChegada;
		this.passagens = passagens;
		this.destino = destino;
		this.aeronave = aeronave;
	}

	public void addPassagem(Passagem passagem) {	
		if(passagens == null)
			passagens = new ArrayList<>();
		passagens.add(passagem);
		passagem.setVoo(this);	
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Calendar getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(Calendar dataChegada) {
		this.dataChegada = dataChegada;
	}

	public List<Passagem> getPassagens() {
		return passagens;
	}

	public void setPassagens(List<Passagem> passagens) {
		this.passagens = passagens;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public Aeronave getAeronave() {
		return aeronave;
	}

	public void setAeronave(Aeronave aeronave) {
		this.aeronave = aeronave;
	}
	
	
	
}
