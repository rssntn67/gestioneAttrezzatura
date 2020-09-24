package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.ModelloConsumabile;
import it.arsinfo.ga.model.entity.OperazioneConsumabile;

public interface ConsumabileService extends OperabileService<ModelloConsumabile,Consumabile,OperazioneConsumabile>{

	List<Consumabile> searchBy(StatoOperabile searchStatoConsumabile, String searchIdentificativo, ModelloConsumabile searchModello);

}
