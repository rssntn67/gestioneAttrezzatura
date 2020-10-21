package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Operabile;
import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.model.entity.Operazione;

public interface OperazioneService<T extends Operabile<?>, S extends Operazione<T>> {

	void esegui(S operazione) throws Exception;
	List<Cantiere> getCantieri();
	List<Operatore> getOperatori();
        List<Cantiere> getCantieriInOpera();
        List<Operatore> getOperatoriAttivi();
	List<T> getOperabili();
	List<S> findAll();	
	List<S> searchBy(Cantiere cantiere, T operabile, TipoOperazione t, Operatore operatore);
	S getLastOperation(T operabile);

}
