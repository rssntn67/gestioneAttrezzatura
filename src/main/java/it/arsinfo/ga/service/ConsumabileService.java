package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.ModelloConsumabile;

public interface ConsumabileService extends EntityBaseService<Consumabile>{

	List<Consumabile> searchBy(StatoOperabile searchStatoConsumabile, String searchIdentificativo, ModelloConsumabile searchModello);

	List<ModelloConsumabile> getModelli();

}
