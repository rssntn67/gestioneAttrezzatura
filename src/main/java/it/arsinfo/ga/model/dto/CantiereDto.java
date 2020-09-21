package it.arsinfo.ga.model.dto;

import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.TipoCantiere;

public class CantiereDto {
    private String identificativo;
    private TipoCantiere tipo=TipoCantiere.Spazzamento;
    private StatoCantiere stato=StatoCantiere.InOpera;
    private String sitoIn;
    
	public String getIdentificativo() {
		return identificativo;
	}
	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}
	public TipoCantiere getTipo() {
		return tipo;
	}
	public void setTipo(TipoCantiere tipo) {
		this.tipo = tipo;
	}
	public StatoCantiere getStato() {
		return stato;
	}
	public void setStato(StatoCantiere stato) {
		this.stato = stato;
	}
	public String getSitoIn() {
		return sitoIn;
	}
	public void setSitoIn(String sitoIn) {
		this.sitoIn = sitoIn;
	}
    

}
