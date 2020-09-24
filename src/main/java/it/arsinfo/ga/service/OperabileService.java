package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.entity.Modello;
import it.arsinfo.ga.model.entity.Operabile;
import it.arsinfo.ga.model.entity.Operazione;


public interface OperabileService<M extends Modello,T extends Operabile<M>, S extends Operazione<T>> extends EntityBaseService<T>{
	
	T findByIdentificativo(String identificatico);
	List<M> getModelli();
	List<S> findOperazioni(T t);
}
