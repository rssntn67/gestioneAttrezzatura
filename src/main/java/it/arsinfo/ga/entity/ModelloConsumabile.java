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

import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.Fornitore;
import it.arsinfo.ga.data.MarcaConsumabile;
import it.arsinfo.ga.data.TipoConsumabile;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"nome","fornitore","annoProduzione"})
})
public class ModelloConsumabile extends Modello {
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MarcaConsumabile marca = MarcaConsumabile.NonDefinita;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConsumabile tipo = TipoConsumabile.NonDisponibile;    

    private BigDecimal importo=BigDecimal.ZERO;
	 
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

	public MarcaConsumabile getMarca() {
		return marca;
	}

	public TipoConsumabile getTipo() {
		return tipo;
	}

	public void setTipo(TipoConsumabile tipoModello) {
		this.tipo = tipoModello;
	}


	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public void setMarca(MarcaConsumabile marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "ModelloConsumabile [marca=" + marca + ", tipo=" + tipo + ", importo=" + importo + ", id=" + id
				+ ", nome=" + nome + ", descrizione=" + descrizione + ", fornitore=" + fornitore + ", annoProduzione="
				+ annoProduzione + "]";
	}

}
