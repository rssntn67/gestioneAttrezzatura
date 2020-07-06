package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import it.arsinfo.ga.data.StatoOperabile;
import it.arsinfo.ga.entity.Personale;
import it.arsinfo.ga.entity.ModelloPersonale;

@Service
public interface PersonaleServiceDao extends ServiceDao<Personale>{

	List<Personale> searchBy(StatoOperabile searchStatoPersonale, String searchIdentificativo, ModelloPersonale searchModello);

	List<ModelloPersonale> getModelli();

}
