package it.arsinfo.ga.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import it.arsinfo.ga.data.StatoPersonale;
import it.arsinfo.ga.data.TipoPersonale;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"identificativo"})
        })
public class Personale implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPersonale tipoPersonale = TipoPersonale.Operai;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoPersonale statoPersonale=StatoPersonale.Disponibile;
    
    private BigDecimal importo=BigDecimal.ZERO;
    private Integer numero=0;
    private Integer disponibili=0;

	@Override
	public Long getId() {
		return id;
	}

	@Override
    @Transient
    public String getHeader() {
        return String.format("%s:%s:%s:%d", tipoPersonale,statoPersonale,numero);
    }

	public TipoPersonale getTipoPersonale() {
		return tipoPersonale;
	}

	public void setTipoPersonale(TipoPersonale tipoPersonale) {
		this.tipoPersonale = tipoPersonale;
	}

	public StatoPersonale getStatoPersonale() {
		return statoPersonale;
	}

	public void setStatoPersonale(StatoPersonale statoPersonale) {
		this.statoPersonale = statoPersonale;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getDisponibili() {
		return disponibili;
	}

	public void setDisponibili(Integer disponibili) {
		this.disponibili = disponibili;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Personale [id=" + id + ", tipoPersonale=" + tipoPersonale + ", statoPersonale=" + statoPersonale
				+ ", importo=" + importo + ", numero=" + numero + ", disponibili=" + disponibili + "]";
	}
	
	

}
