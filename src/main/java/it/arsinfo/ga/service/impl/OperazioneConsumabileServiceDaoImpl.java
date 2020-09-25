package it.arsinfo.ga.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.arsinfo.ga.dao.ConsumabileDao;
import it.arsinfo.ga.dao.OperazioneConsumabileDao;
import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.ModelloConsumabile;
import it.arsinfo.ga.model.entity.OperazioneConsumabile;
import it.arsinfo.ga.service.OperazioneService;

@Service
public class OperazioneConsumabileServiceDaoImpl extends OperazioneServiceDaoImpl<ModelloConsumabile,Consumabile,OperazioneConsumabile> implements OperazioneService<Consumabile, OperazioneConsumabile> {
	
	@Autowired
	private ConsumabileDao operabileDao;

	@Autowired
	private OperazioneConsumabileDao operazioneDao;
	
    private static final Logger log = LoggerFactory.getLogger(OperazioneConsumabileServiceDaoImpl.class);

	@Override
	@Transactional
	public void esegui(OperazioneConsumabile operazione) throws Exception {
		Consumabile operabile = check(operazione);
		if (operazione.getNumero() > operabile.getDisponibili()) {
			log.error("esegui: Q.tà operazione {} minore Q.tà disponibile {}, {}", operazione.getNumero(), operabile.getNumero(),operabile.getIdentificativo());
			throw new UnsupportedOperationException("Numero operabile minore disponibile");
		}
		switch (operazione.getTipoOperazione()) {
			case Carico:
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
		operabile.setUtilizzati(operabile.getUtilizzati()+operazione.getNumero());
		if (operabile.getDisponibili() == 0) {
			operabile.setStato(StatoOperabile.Occupato);
		}
		log.info("esegui: {}, {}, {}, {}, {}",operazione.getTipoOperazione(),operazione.getCantiere().getIdentificativo(),operabile.getIdentificativo(),operazione.getOperatore().getIdentificativo(),operazione.getNumero());
		operabileDao.save(operabile);
		operazioneDao.save(operazione);						
	}
	
}
