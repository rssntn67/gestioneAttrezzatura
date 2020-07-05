package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.Fornitore;
import it.arsinfo.ga.data.MarcaConsumabile;
import it.arsinfo.ga.data.TipoConsumabile;
import it.arsinfo.ga.entity.ModelloConsumabile;

@Service
public interface ModelloConsumabileServiceDao extends ServiceDao<ModelloConsumabile>{

	List<ModelloConsumabile> searchBy(Fornitore fornitore, Anno searchAnnoProduzione, String searchNome, TipoConsumabile searchTipoModello,
			MarcaConsumabile searchMarcaModello);

}
