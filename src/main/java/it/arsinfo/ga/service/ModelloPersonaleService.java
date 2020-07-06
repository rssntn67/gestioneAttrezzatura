package it.arsinfo.ga.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.TipoPersonale;
import it.arsinfo.ga.model.entity.ModelloPersonale;

@Service
public interface ModelloPersonaleService extends ServiceDao<ModelloPersonale>{

	List<ModelloPersonale> searchBy(Fornitore fornitore, Anno searchAnnoProduzione, String searchNome, TipoPersonale searchTipoModello);

}
