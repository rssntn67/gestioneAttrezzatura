package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.Fornitore;
import it.arsinfo.ga.data.TipoPersonale;
import it.arsinfo.ga.entity.ModelloPersonale;

@Service
public interface ModelloPersonaleServiceDao extends ServiceDao<ModelloPersonale>{

	List<ModelloPersonale> searchBy(Fornitore fornitore, Anno searchAnnoProduzione, String searchNome, TipoPersonale searchTipoModello);

}
