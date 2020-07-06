package it.arsinfo.ga.service;

import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Modello;
import it.arsinfo.ga.model.entity.Operabile;

public interface OperazioneService<K extends Modello,T extends Operabile<K>> {

	void esegui(TipoOperazione tipo, Cantiere cantiere, T operabile, int numero) throws UnsupportedOperationException;
	
}
