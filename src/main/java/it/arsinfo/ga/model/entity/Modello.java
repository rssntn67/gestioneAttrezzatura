package it.arsinfo.ga.model.entity;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;

public abstract class Modello implements EntityBase {

	public abstract String getNome();
	public abstract Fornitore getFornitore();
	public abstract Anno getAnnoProduzione();
	public abstract String getDescrizione();
	
	public abstract void setNome(String nome);
	public abstract void setFornitore(Fornitore fornitore);
	public abstract void setAnnoProduzione(Anno anno);
	public abstract void setDescrizione(String descrizione);
	
}
