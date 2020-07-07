package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Modello;
import it.arsinfo.ga.model.entity.Operabile;
import it.arsinfo.ga.model.entity.Operazione;

public interface OperazioneService<K extends Modello,T extends Operabile<K>, S extends Operazione<K, T>> {

	void esegui(S operazione) throws Exception;
	List<Cantiere> getCantieri();
	List<T> getOperabili();	
}
