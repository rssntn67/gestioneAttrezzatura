package it.arsinfo.ga.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.arsinfo.ga.dao.OperazionePersonaleDao;
import it.arsinfo.ga.dao.PersonaleDao;
import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.model.entity.OperazionePersonale;
import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.service.OperazioneService;

@Service
public class OperazionePersonaleServiceDaoImpl extends OperazioneServiceDaoImpl<ModelloPersonale,Personale,OperazionePersonale>implements OperazioneService<Personale, OperazionePersonale> {
	
	@Autowired
	private PersonaleDao operabileDao;

	@Autowired
	private OperazionePersonaleDao operazioneDao;
	
    private static final Logger log = LoggerFactory.getLogger(OperazionePersonaleServiceDaoImpl.class);

	@Override
	@Transactional
	public void esegui(OperazionePersonale operazione) throws Exception {
		Personale operabile = check(operazione);
		switch (operazione.getTipoOperazione()) {
			case Carico:
				if (operazione.getNumero() > operabile.getDisponibili()) {
					log.error("esegui: Q.tà operazione {} minore Q.tà disponibile {}, {}", operazione.getNumero(), operabile.getNumero(),operabile.getIdentificativo());
					throw new UnsupportedOperationException("Numero operabile minore disponibile");
				}
				operabile.setUtilizzati(operabile.getUtilizzati()+operazione.getNumero());
				if (operabile.getDisponibili() == 0) {
					operabile.setStato(StatoOperabile.Occupato);
				}
				break;
			case Scarico:
				if (operazione.getNumero() > operabile.getUtilizzati()) {
					log.error("esegui: Q.tà operazione {} maggiore Q.tà utilizzati {}, {}", operazione.getNumero(), operabile.getUtilizzati(),operabile.getIdentificativo());
					throw new UnsupportedOperationException("Numero operabile maggiore utilizzati");
				}
				operabile.setUtilizzati(operabile.getUtilizzati()-operazione.getNumero());
				if (operabile.getDisponibili() > 0) {
					operabile.setStato(StatoOperabile.Disponibile);
				}
				break;
			case Furto:
				throw new UnsupportedOperationException("Operazione Scarico non disponibile");
			case Rottura:
				throw new UnsupportedOperationException("Operazione Scarico non disponibile");
			case Smarrimento:
				throw new UnsupportedOperationException("Operazione Scarico non disponibile");
			default:
				break;
		}		
		log.info("esegui: {}, {}, {}, {}, {}",operazione.getTipoOperazione(),operazione.getCantiere().getIdentificativo(),operabile.getIdentificativo(),operazione.getOperatore().getIdentificativo(),operazione.getNumero());
		operabileDao.save(operabile);
		operazioneDao.save(operazione);						
	}
}
