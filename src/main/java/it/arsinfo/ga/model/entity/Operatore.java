package it.arsinfo.ga.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import it.arsinfo.ga.model.data.StatoOperatore;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"identificativo"}),
        @UniqueConstraint(columnNames = {"apikey"})
        })

public class Operatore implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String apikey;
    private String email;
    private String identificativo;
    private String telefono;
    private StatoOperatore stato = StatoOperatore.Attivo;
    
    
	@Override
	public Long getId() {
		return id;
	}

	@Override
    @Transient
    public String getHeader() {
        return String.format("%s", identificativo);
    }

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apiKey) {
		this.apikey = apiKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String eMail) {
		this.email = eMail;
	}

	public String getIdentificativo() {
		return identificativo;
	}

	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public StatoOperatore getStato() {
		return stato;
	}

	public void setStato(StatoOperatore stato) {
		this.stato = stato;
	}

	@Override
	public String toString() {
		return "Operatore [id=" + id + ", apiKey=" + apikey + ", eMail=" + email + ", identificativo=" + identificativo
				+ ", telefono=" + telefono + ", stato=" + stato + "]";
	}

}
