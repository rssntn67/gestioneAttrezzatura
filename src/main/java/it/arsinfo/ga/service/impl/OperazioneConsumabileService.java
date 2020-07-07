package it.arsinfo.ga.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.arsinfo.ga.dao.ConsumabileDao;
import it.arsinfo.ga.dao.CantiereDao;
import it.arsinfo.ga.dao.OperazioneConsumabileDao;
import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.ModelloConsumabile;
import it.arsinfo.ga.model.entity.OperazioneConsumabile;
import it.arsinfo.ga.service.OperazioneService;

@Service
public class OperazioneConsumabileService implements OperazioneService<ModelloConsumabile, Consumabile, OperazioneConsumabile> {
	
	@Autowired
	private ConsumabileDao operabileDao;

	@Autowired
	private CantiereDao cantiereDao;

	@Autowired
	private OperazioneConsumabileDao operazioneDao;
	
    private static final Logger log = LoggerFactory.getLogger(OperazioneConsumabileService.class);

	@Override
	@Transactional
	public void esegui(OperazioneConsumabile operazione) throws Exception {
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
		Consumabile operabile = operabileDao.findById(operazione.getOperabile().getId()).get();
		log.info("esegui: {}, {}, {}",operazione.getTipoOperazione(),cantiere,operabile);
		if (operazione.getNumero() > operabile.getDisponibili()) {
			throw new UnsupportedOperationException("Numero operabile minore disponibile");
		}
		switch (operazione.getTipoOperazione()) {
			case Carico:
				operabile.setUtilizzati(operabile.getUtilizzati()+operazione.getNumero());
				if (operabile.getDisponibili() == 0) {
					operabile.setStato(StatoOperabile.Occupato);
				}
				break;
			case Scarico:
				throw new UnsupportedOperationException("Operazione Scarico non disponibile");
			case Furto:
				break;
			case Rottura:
				break;
			case Smarrimento:
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
	public List<Consumabile> getOperabili() {
		return operabileDao.findByStato(StatoOperabile.Disponibile);
	}
}
