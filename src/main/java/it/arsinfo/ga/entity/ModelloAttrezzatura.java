package it.arsinfo.ga.entity;

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

import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.MarcaModello;
import it.arsinfo.ga.data.TipoModello;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"nome"})
        })
public class ModelloAttrezzatura implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String descrizione;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MarcaModello marcaModello = MarcaModello.NonDefinita;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoModello tipoModello = TipoModello.NonDisponibile;    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Anno annoProduzione = Anno.ANNOND;

	@Override
	public Long getId() {
		return id;
	}

	@Override
    @Transient
    public String getHeader() {
        return String.format("%s:%s", nome,annoProduzione.getAnnoAsString());
    }
	 
	public MarcaModello getMarcaModello() {
		return marcaModello;
	}

	public void setMarcaModello(MarcaModello marcaModello) {
		this.marcaModello = marcaModello;
	}

	public TipoModello getTipoModello() {
		return tipoModello;
	}

	public void setTipoModello(TipoModello tipoModello) {
		this.tipoModello = tipoModello;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Anno getAnnoProduzione() {
		return annoProduzione;
	}

	public void setAnnoProduzione(Anno annoProduzione) {
		this.annoProduzione = annoProduzione;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ModelloAttrezzatura [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", marcaModello="
				+ marcaModello + ", tipoModello=" + tipoModello + ", annoProduzione=" + annoProduzione + "]";
	}

}
