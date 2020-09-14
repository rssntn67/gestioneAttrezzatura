package it.arsinfo.ga.model.entity;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;

public interface Modello extends EntityBase {

	String getNome();
	Fornitore getFornitore();
	Anno getAnnoProduzione();
	String getDescrizione();
	
	void setNome(String nome);
	void setFornitore(Fornitore fornitore);
	void setAnnoProduzione(Anno anno);
	void setDescrizione(String descrizione);
	
}
