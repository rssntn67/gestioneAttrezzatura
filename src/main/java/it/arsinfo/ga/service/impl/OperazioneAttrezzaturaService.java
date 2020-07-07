package it.arsinfo.ga.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.arsinfo.ga.dao.AttrezzaturaDao;
import it.arsinfo.ga.dao.CantiereDao;
import it.arsinfo.ga.dao.OperazioneAttrezzaturaDao;
import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.service.OperazioneService;

@Service
public class OperazioneAttrezzaturaService implements OperazioneService<ModelloAttrezzatura, Attrezzatura, OperazioneAttrezzatura> {
	
	@Autowired
	private AttrezzaturaDao operabileDao;

	@Autowired
	private CantiereDao cantiereDao;

	@Autowired
	private OperazioneAttrezzaturaDao operazioneDao;
	
    private static final Logger log = LoggerFactory.getLogger(OperazioneAttrezzaturaService.class);

	@Override
	@Transactional
	public void esegui(OperazioneAttrezzatura operazione) throws Exception {
		if (operazione.getTipoOperazione() == null)
			throw new UnsupportedOperationException("TipoOperazione non può essere null");
		if (operazione.getOperabile() == null)
			throw new UnsupportedOperationException("Operabile non può essere null");
		if (operazione.getCantiere() == null)
			throw new UnsupportedOperationException("Cantiere non può essere null");
		Cantiere cantiere = cantiereDao.findById(operazione.getCantiere().getId()).get();
		if (cantiere.getStato() != StatoCantiere.InOpera) {
			throw new UnsupportedOperationException("Stato Cantiere non operabile: " + cantiere.getStato());			
		}
		Attrezzatura operabile = operabileDao.findById(operazione.getOperabile().getId()).get();
		log.info("esegui: {}, {}, {}",operazione.getTipoOperazione(),cantiere,operabile);
		switch (operazione.getTipoOperazione()) {
			case Carico:
				operabile.setStato(StatoOperabile.Occupato);
				break;
			case Scarico:
				operabile.setStato(StatoOperabile.Disponibile);
				break;
			case Furto:
				operabile.setStato(StatoOperabile.Dismesso);
				break;
			case Rottura:
				operabile.setStato(StatoOperabile.Dismesso);
				break;
			case Smarrimento:
				operabile.setStato(StatoOperabile.Dismesso);
				break;
	
			default:
				break;
		}		
		operabileDao.save(operabile);
		operazioneDao.save(operazione);						
	}

	@Override
	public List<Cantiere> getCantieri() {
		return cantiereDao.findByStato(StatoCantiere.InOpera);
	}

	@Override
	public List<Attrezzatura> getOperabili() {
		return operabileDao.findAll();
	}
}
