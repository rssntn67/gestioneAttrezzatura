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
import it.arsinfo.ga.data.MarcaAttrezzatura;
import it.arsinfo.ga.data.TipoAttrezzatura;

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
    private MarcaAttrezzatura marca = MarcaAttrezzatura.NonDefinita;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAttrezzatura tipo = TipoAttrezzatura.NonDisponibile;    
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
	 
	public MarcaAttrezzatura getMarca() {
		return marca;
	}

	public void setMarcaModello(MarcaAttrezzatura marcaModello) {
		this.marca = marcaModello;
	}

	public TipoAttrezzatura getTipo() {
		return tipo;
	}

	public void setTipo(TipoAttrezzatura tipoModello) {
		this.tipo = tipoModello;
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
		return "ModelloAttrezzatura [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", marca="
				+ marca + ", tipo=" + tipo + ", annoProduzione=" + annoProduzione + "]";
	}

}
