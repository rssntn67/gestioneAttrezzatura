package it.arsinfo.ga.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import it.arsinfo.ga.model.data.StatoOperabile;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"identificativo"})
        })
public class Attrezzatura extends Operabile<ModelloAttrezzatura> {
    
    @ManyToOne(fetch=FetchType.EAGER)
    private ModelloAttrezzatura modello;
    
    
    private BigDecimal speseManutenzione=BigDecimal.ZERO;
    private BigDecimal speseRiparazione=BigDecimal.ZERO;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String identificativo;
        
	@Override
	public Long getId() {
		return id;
	}

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoOperabile stato=StatoOperabile.Disponibile;

    @Override
	public String getIdentificativo() {
		return identificativo;
	}

    @Override
	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}

	public StatoOperabile getStato() {
		return stato;
	}

	public void setStato(StatoOperabile stato) {
		this.stato = stato;
	}

	@Override
    @Transient
    public String getHeader() {
        return String.format("%s:%s:%s", identificativo,getModello().getHeader(),stato);
    }


    @Override
	public ModelloAttrezzatura getModello() {
		return modello;
	}

    @Override
	public void setModello(ModelloAttrezzatura modelloAttrezzatura) {
		this.modello = modelloAttrezzatura;
	}

	public BigDecimal getSpeseManutenzione() {
		return speseManutenzione;
	}

	public void setSpeseManutenzione(BigDecimal speseManutenzione) {
		this.speseManutenzione = speseManutenzione;
	}

	public BigDecimal getSpeseRiparazione() {
		return speseRiparazione;
	}

	public void setSpeseRiparazione(BigDecimal speseRiparazione) {
		this.speseRiparazione = speseRiparazione;
	}

	@Override
	public String toString() {
		return "Attrezzatura [modelloAttrezzatura=" + modello 
				+ ", speseManutenzione=" + speseManutenzione + ", speseRiparazione=" + speseRiparazione + ", id=" + id
				+ ", identificativo=" + identificativo + ", stato=" + stato + "]";
	}

}
