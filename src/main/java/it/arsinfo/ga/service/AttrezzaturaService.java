package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;

public interface AttrezzaturaService extends OperabileService<ModelloAttrezzatura,Attrezzatura,OperazioneAttrezzatura>{

	List<Attrezzatura> searchBy(StatoOperabile searchStatoAttrezzatura, String searchIdentificativo, ModelloAttrezzatura searchModello);

}
