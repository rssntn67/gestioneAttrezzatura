package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.entity.Modello;


public interface ModelloService<M extends Modello> extends EntityBaseService<M>{
	
	List<M> findByNomeAndFornitoreAndAnnoProduzione(String nome, Fornitore fornitore, Anno anno);
}
