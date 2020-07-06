package it.arsinfo.ga.model.entity;

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

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.MarcaAttrezzatura;
import it.arsinfo.ga.model.data.TipoAttrezzatura;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"nome","fornitore","annoProduzione"})
})
public class ModelloAttrezzatura extends Modello {
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MarcaAttrezzatura marca = MarcaAttrezzatura.NonDefinita;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAttrezzatura tipo = TipoAttrezzatura.NonDisponibile;    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String descrizione;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Fornitore fornitore = Fornitore.NonDisponibile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Anno annoProduzione = Anno.ANNOND;

    private BigDecimal costo=BigDecimal.ZERO;

	@Override
	public Long getId() {
		return id;
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

	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public Anno getAnnoProduzione() {
		return annoProduzione;
	}

	public void setAnnoProduzione(Anno annoProduzione) {
		this.annoProduzione = annoProduzione;
	}

	@Override
    @Transient
    public String getHeader() {
        return String.format("%s:%s", nome,annoProduzione.getAnnoAsString());
    }

	public MarcaAttrezzatura getMarca() {
		return marca;
	}

	public TipoAttrezzatura getTipo() {
		return tipo;
	}

	public void setTipo(TipoAttrezzatura tipoModello) {
		this.tipo = tipoModello;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public void setMarca(MarcaAttrezzatura marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "ModelloAttrezzatura [marca=" + marca + ", tipo=" + tipo + ", id=" + id + ", nome=" + nome
				+ ", descrizione=" + descrizione + ", fornitore=" + fornitore + ", annoProduzione=" + annoProduzione
				+ ", costo=" + costo + "]";
	}

}
