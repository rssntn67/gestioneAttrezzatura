package it.arsinfo.ga.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.ModelloConsumabile;

@Service
public interface ConsumabileService extends ServiceDao<Consumabile>{

	List<Consumabile> searchBy(StatoOperabile searchStatoConsumabile, String searchIdentificativo, ModelloConsumabile searchModello);

	List<ModelloConsumabile> getModelli();

}
