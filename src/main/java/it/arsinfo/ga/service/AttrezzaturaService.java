package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;

public interface AttrezzaturaService extends EntityBaseService<Attrezzatura>{

	List<Attrezzatura> searchBy(StatoOperabile searchStatoAttrezzatura, String searchIdentificativo, ModelloAttrezzatura searchModello);

	List<ModelloAttrezzatura> getModelli();

}
