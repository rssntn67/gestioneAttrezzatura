package it.arsinfo.ga.entity;

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

import it.arsinfo.ga.data.StatoAttrezzatura;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"identificativo"})
        })
public class Attrezzatura implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String identificativo;
    
    @ManyToOne(fetch=FetchType.EAGER)
    private ModelloAttrezzatura modelloAttrezzatura;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoAttrezzatura statoAttrezzatura=StatoAttrezzatura.Disponibile;
    
	@Override
	public Long getId() {
		return id;
	}

	@Override
    @Transient
    public String getHeader() {
        return String.format("%s:%s:%s", identificativo,modelloAttrezzatura.getHeader(),statoAttrezzatura);
    }

    @Transient
    public String getModello() {
    	if (modelloAttrezzatura != null)
    		return modelloAttrezzatura.getHeader();
    	return "NA";
    }

	public String getIdentificativo() {
		return identificativo;
	}

	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}

	public ModelloAttrezzatura getModelloAttrezzatura() {
		return modelloAttrezzatura;
	}

	public void setModelloAttrezzatura(ModelloAttrezzatura modelloAttrezzatura) {
		this.modelloAttrezzatura = modelloAttrezzatura;
	}

	public StatoAttrezzatura getStatoAttrezzatura() {
		return statoAttrezzatura;
	}

	public void setStatoAttrezzatura(StatoAttrezzatura statoAttrezzatura) {
		this.statoAttrezzatura = statoAttrezzatura;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Attrezzatura [id=" + id + ", identificativo=" + identificativo + ", modelloAttrezzatura="
				+ modelloAttrezzatura + ", statoAttrezzatura=" + statoAttrezzatura + "]";
	}

}
