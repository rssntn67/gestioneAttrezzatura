package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.TipoCantiere;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.model.entity.OperazioneConsumabile;
import it.arsinfo.ga.model.entity.OperazionePersonale;

public interface CantiereService extends EntityBaseService<Cantiere>{

	List<Cantiere> searchBy(String searchIdentificativo, StatoCantiere statoCantiere, TipoCantiere tipo);
	Cantiere findByIdentificativo(String identificatico);
	List<OperazioneAttrezzatura> findOperazioneAttrezzatura(Cantiere cantiere);
	List<OperazioneConsumabile> findOperazioneConsumabile(Cantiere cantiere);
	List<OperazionePersonale> findOperazionePersonale(Cantiere cantiere);

}
