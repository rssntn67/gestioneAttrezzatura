package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.MarcaAttrezzatura;
import it.arsinfo.ga.model.data.TipoAttrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;

public interface ModelloAttrezzaturaService extends EntityBaseService<ModelloAttrezzatura>{

	List<ModelloAttrezzatura> searchBy(Fornitore fornitore, Anno searchAnnoProduzione, String searchNome, TipoAttrezzatura searchTipoModello,
			MarcaAttrezzatura searchMarcaModello);

}
