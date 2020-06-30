package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import it.arsinfo.ga.data.StatoAttrezzatura;
import it.arsinfo.ga.entity.Attrezzatura;
import it.arsinfo.ga.entity.ModelloAttrezzatura;

@Service
public interface AttrezzaturaServiceDao extends ServiceDao<Attrezzatura>{

	List<Attrezzatura> searchBy(StatoAttrezzatura searchStatoAttrezzatura, String searchIdentificativo, ModelloAttrezzatura searchModello);

	List<ModelloAttrezzatura> getModelli();

}
