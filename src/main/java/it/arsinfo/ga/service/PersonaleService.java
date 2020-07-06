package it.arsinfo.ga.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.model.entity.Personale;

@Service
public interface PersonaleService extends ServiceDao<Personale>{

	List<Personale> searchBy(StatoOperabile searchStatoPersonale, String searchIdentificativo, ModelloPersonale searchModello);

	List<ModelloPersonale> getModelli();

}
