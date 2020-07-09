package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.entity.Modello;
import it.arsinfo.ga.model.entity.Operabile;


public interface OperabileService<M extends Modello, O extends Operabile<M>> extends EntityBaseService<O>{
	
	O findByIdentificativo(String identificatico);
	List<M> getModelli();
}
