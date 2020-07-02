package it.arsinfo.ga.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import it.arsinfo.ga.data.TipoOperazionePersonale;

@Entity
public class OperazionePersonale implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private Personale consumabile;

    @ManyToOne(fetch=FetchType.LAZY)
    private Cantiere cantiere;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoOperazionePersonale tipoOperazione=TipoOperazionePersonale.Esce;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataOperazione = new Date();

    private Integer numero = 0;
	@Override
	public Long getId() {
		return id;
	}

	@Override
    @Transient
    public String getHeader() {
        return String.format("%s:%s:%s:%d",cantiere.getHeader(),consumabile.getHeader(),tipoOperazione,numero);
    }

	public Personale getPersonale() {
		return consumabile;
	}

	public void setPersonale(Personale attrezzatura) {
		this.consumabile = attrezzatura;
	}

	public Cantiere getCantiere() {
		return cantiere;
	}

	public void setCantiere(Cantiere cantiere) {
		this.cantiere = cantiere;
	}

	public TipoOperazionePersonale getTipoOperazione() {
		return tipoOperazione;
	}

	public void setTipoOperazione(TipoOperazionePersonale tipoOperazione) {
		this.tipoOperazione = tipoOperazione;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataOperazione() {
		return dataOperazione;
	}

	public void setDataOperazione(Date dataOperazione) {
		this.dataOperazione = dataOperazione;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "OperazionePersonale [id=" + id + ", consumabile=" + consumabile + ", cantiere=" + cantiere
				+ ", tipoOperazione=" + tipoOperazione + ", dataOperazione=" + dataOperazione + ", numero=" + numero
				+ "]";
	}

}
