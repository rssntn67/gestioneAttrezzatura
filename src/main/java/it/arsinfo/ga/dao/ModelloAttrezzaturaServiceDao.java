package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.MarcaAttrezzatura;
import it.arsinfo.ga.data.TipoAttrezzatura;
import it.arsinfo.ga.entity.ModelloAttrezzatura;

@Service
public interface ModelloAttrezzaturaServiceDao extends ServiceDao<ModelloAttrezzatura>{

	List<ModelloAttrezzatura> searchBy(Anno searchAnnoProduzione, String searchNome, TipoAttrezzatura searchTipoModello,
			MarcaAttrezzatura searchMarcaModello);

}
