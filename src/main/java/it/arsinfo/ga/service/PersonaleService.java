package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.model.entity.Personale;

public interface PersonaleService extends EntityBaseService<Personale>{
	List<Personale> searchBy(StatoOperabile searchStatoPersonale, String searchIdentificativo, ModelloPersonale searchModello);

	List<ModelloPersonale> getModelli();

}
