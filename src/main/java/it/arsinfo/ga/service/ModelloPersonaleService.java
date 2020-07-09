package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.TipoPersonale;
import it.arsinfo.ga.model.entity.ModelloPersonale;

public interface ModelloPersonaleService extends ModelloService<ModelloPersonale>{

	List<ModelloPersonale> searchBy(Fornitore fornitore, Anno searchAnnoProduzione, String searchNome, TipoPersonale searchTipoModello);

}
