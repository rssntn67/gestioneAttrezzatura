package it.arsinfo.ga.model.dto;

import it.arsinfo.ga.model.data.StatoOperabile;

public class OperabileDto {
    
    private String modello;
    private String identificativo;        
    private StatoOperabile stato=StatoOperabile.Disponibile;

	public String getIdentificativo() {
		return identificativo;
	}

	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}

	public StatoOperabile getStato() {
		return stato;
	}

	public void setStato(StatoOperabile stato) {
		this.stato = stato;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modelloAttrezzatura) {
		this.modello = modelloAttrezzatura;
	}
}
