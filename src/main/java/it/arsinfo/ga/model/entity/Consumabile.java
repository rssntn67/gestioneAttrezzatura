package it.arsinfo.ga.model.entity;

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
public class Consumabile implements Operabile<ModelloConsumabile> {

    @ManyToOne(fetch=FetchType.EAGER)
    private ModelloConsumabile modello;
    
    
    private Integer numero=0;
    private Integer utilizzati=0;

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
    @Transient
    public String getCode() {
        return identificativo;
    }

    @Override
	public ModelloConsumabile getModello() {
		return modello;
	}

    @Override
	public void setModello(ModelloConsumabile modelloConsumabile) {
		this.modello = modelloConsumabile;
	}


	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getDisponibili() {
		return numero-utilizzati;
	}


	@Override
	public String toString() {
		return "Consumabile [modelloConsumabile=" + modello + ", numero=" + numero + ", utilizzati="
				+ utilizzati + ", id=" + id + ", identificativo=" + identificativo + ", stato=" + stato + "]";
	}

	public Integer getUtilizzati() {
		return utilizzati;
	}

	public void setUtilizzati(Integer utilizzati) {
		this.utilizzati = utilizzati;
	}

}
