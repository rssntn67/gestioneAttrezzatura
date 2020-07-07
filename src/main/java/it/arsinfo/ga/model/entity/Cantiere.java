package it.arsinfo.ga.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.TipoCantiere;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"identificativo"})
        })
public class Cantiere implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Override
    @Transient
    public String getHeader() {
        return identificativo;
    }

    private String identificativo;

    @Enumerated(EnumType.STRING)
    private TipoCantiere tipo=TipoCantiere.Spazzamento;
    @Enumerated(EnumType.STRING)
    private StatoCantiere stato=StatoCantiere.InOpera;

    private String sitoIn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date aperturaCantiere = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date chiusuraCantiere = new Date();
	
    Integer distanzaChilometrica=0;
    public String getIdentificativo() {
		return identificativo;
	}

	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}

	public StatoCantiere getStato() {
		return stato;
	}

	public void setStato(StatoCantiere statoCantiere) {
		this.stato = statoCantiere;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSitoIn() {
		return sitoIn;
	}

	public void setSitoIn(String sitoIn) {
		this.sitoIn = sitoIn;
	}

	public Date getAperturaCantiere() {
		return aperturaCantiere;
	}

	public void setAperturaCantiere(Date aperturaCantiere) {
		this.aperturaCantiere = aperturaCantiere;
	}

	public Date getChiusuraCantiere() {
		return chiusuraCantiere;
	}

	public void setChiusuraCantiere(Date chiusuraCantiere) {
		this.chiusuraCantiere = chiusuraCantiere;
	}

	public Integer getDistanzaChilometrica() {
		return distanzaChilometrica;
	}

	public void setDistanzaChilometrica(Integer distanzaChilometrica) {
		this.distanzaChilometrica = distanzaChilometrica;
	}

	public TipoCantiere getTipo() {
		return tipo;
	}

	public void setTipo(TipoCantiere tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Cantiere [id=" + id + ", identificativo=" + identificativo + ", tipo=" + tipo + ", statoCantiere="
				+ stato + ", sitoIn=" + sitoIn + ", aperturaCantiere=" + aperturaCantiere
				+ ", chiusuraCantiere=" + chiusuraCantiere + ", distanzaChilometrica=" + distanzaChilometrica + "]";
	}
    
    
    
}
