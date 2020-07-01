package it.arsinfo.ga.entity;

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

import it.arsinfo.ga.data.StatoConsumabile;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"identificativo"})
        })
public class Consumabile implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch=FetchType.EAGER)
    private ModelloConsumabile modelloConsumabile;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoConsumabile statoConsumabile=StatoConsumabile.Disponibile;
    
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
        return String.format("%s:%s:%s:%d", modelloConsumabile.getHeader(),statoConsumabile,numero);
    }

    @Transient
    public String getModello() {
    	if (modelloConsumabile != null)
    		return modelloConsumabile.getHeader();
    	return "NA";
    }

	public ModelloConsumabile getModelloConsumabile() {
		return modelloConsumabile;
	}

	public void setModelloConsumabile(ModelloConsumabile modelloConsumabile) {
		this.modelloConsumabile = modelloConsumabile;
	}

	public StatoConsumabile getStatoConsumabile() {
		return statoConsumabile;
	}

	public void setStatoConsumabile(StatoConsumabile statoConsumabile) {
		this.statoConsumabile = statoConsumabile;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Consumabile [id=" + id + ", modelloConsumabile=" + modelloConsumabile + ", statoConsumabile="
				+ statoConsumabile + ", importo=" + importo + ", numero=" + numero + ", disponibili=" + disponibili
				+ "]";
	}

	

}
