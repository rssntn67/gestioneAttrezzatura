package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.data.StatoOperatore;
import it.arsinfo.ga.model.entity.Operatore;

public interface OperatoreService extends EntityBaseService<Operatore>{

	List<Operatore> searchBy(String searchIdentificativo, StatoOperatore stato);
	Operatore findByIdentificativo(String identificatico);
	Operatore findByApikey(String apikey);

}
