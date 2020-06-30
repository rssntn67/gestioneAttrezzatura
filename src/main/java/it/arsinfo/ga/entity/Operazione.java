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

import it.arsinfo.ga.data.TipoOperazione;

@Entity
public class Operazione implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private Attrezzatura attrezzatura;

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
        return String.format("%s:%s:%s",tipoOperazione, cantiere.getHeader(),attrezzatura.getHeader() );
    }

	public Attrezzatura getAttrezzatura() {
		return attrezzatura;
	}

	public void setAttrezzatura(Attrezzatura attrezzatura) {
		this.attrezzatura = attrezzatura;
	}

	public Cantiere getCantiere() {
		return cantiere;
	}

	public void setCantiere(Cantiere cantiere) {
		this.cantiere = cantiere;
	}

	public TipoOperazione getTipoOperazione() {
		return tipoOperazione;
	}

	public void setTipoOperazione(TipoOperazione tipoOperazione) {
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

	@Override
	public String toString() {
		return "Operazione [id=" + id + ", attrezzatura=" + attrezzatura + ", cantiere=" + cantiere
				+ ", tipoOperazione=" + tipoOperazione + ", dataOperazione=" + dataOperazione + "]";
	}

}
