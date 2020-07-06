package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import it.arsinfo.ga.data.StatoOperabile;
import it.arsinfo.ga.entity.Consumabile;
import it.arsinfo.ga.entity.ModelloConsumabile;

@Service
public interface ConsumabileServiceDao extends ServiceDao<Consumabile>{

	List<Consumabile> searchBy(StatoOperabile searchStatoConsumabile, String searchIdentificativo, ModelloConsumabile searchModello);

	List<ModelloConsumabile> getModelli();

}
