package it.arsinfo.ga.model.entity;

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

import it.arsinfo.ga.model.data.TipoOperazione;

@Entity
public class OperazioneConsumabile extends Operazione<ModelloConsumabile,Consumabile> {
    
    @ManyToOne(fetch=FetchType.LAZY)
    private Consumabile operabile;

    private Integer numero = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private Cantiere cantiere;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoOperazione tipoOperazione=TipoOperazione.Carico;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataOperazione = new Date();

	@Override
	public Long getId() {
		return id;
	}

	@Override
    @Transient
    public String getHeader() {
        return String.format("%s:%s:%s",tipoOperazione, cantiere.getHeader(),getOperabile().getHeader() );
    }

    @Transient
    public String getQRCode() {
        return String.format("ClassName:%s\n%s\n%s\n%s\n%s", 
        		this.getClass().getName(),
        		cantiere.getQRCode(),
        		operabile.getQRCode(),
        		tipoOperazione,
        		dataOperazione.toString());
    }

	@Override
	public Cantiere getCantiere() {
		return cantiere;
	}

	@Override
	public void setCantiere(Cantiere cantiere) {
		this.cantiere = cantiere;
	}

	@Override
	public TipoOperazione getTipoOperazione() {
		return tipoOperazione;
	}

	@Override
	public void setTipoOperazione(TipoOperazione tipoOperazione) {
		this.tipoOperazione = tipoOperazione;
	}

	@Override
	public Date getDataOperazione() {
		return dataOperazione;
	}

	@Override
	public void setDataOperazione(Date dataOperazione) {
		this.dataOperazione = dataOperazione;
	}

    @Override
	public Consumabile getOperabile() {
		return operabile;
	}

    @Override
	public void setOperabile(Consumabile attrezzatura) {
		this.operabile = attrezzatura;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "OperazioneConsumabile [consumabile=" + operabile + ", numero=" + numero + ", id=" + id + ", cantiere="
				+ cantiere + ", tipoOperazione=" + tipoOperazione + ", dataOperazione=" + dataOperazione + "]";
	}

}
