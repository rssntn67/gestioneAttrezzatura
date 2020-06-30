package it.arsinfo.ga.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import it.arsinfo.ga.data.StatoCantiere;

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
    private StatoCantiere statoCantiere=StatoCantiere.InOpera;

	public String getIdentificativo() {
		return identificativo;
	}

	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}

	public StatoCantiere getStatoCantiere() {
		return statoCantiere;
	}

	public void setStatoCantiere(StatoCantiere statoCantiere) {
		this.statoCantiere = statoCantiere;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Cantiere [id=" + id + ", identificativo=" + identificativo + ", statoCantiere=" + statoCantiere + "]";
	}
    
    
    
}
