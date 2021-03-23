package it.arsinfo.ga.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.arsinfo.ga.dao.AttrezzaturaDao;
import it.arsinfo.ga.dao.OperazioneAttrezzaturaDao;
import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.model.kafka.KafkaOperazione;
import it.arsinfo.ga.service.OperazioneService;

@Service
@Configuration
public class OperazioneAttrezzaturaServiceDaoImpl extends OperazioneServiceDaoImpl<ModelloAttrezzatura,Attrezzatura,OperazioneAttrezzatura>implements OperazioneService<Attrezzatura, OperazioneAttrezzatura> {
	
	@Autowired
	private AttrezzaturaDao operabileDao;
	
	@Autowired
	private OperazioneAttrezzaturaDao operazioneDao;

	private static final Logger log = LoggerFactory.getLogger(OperazioneAttrezzaturaServiceDaoImpl.class);

	@Override
	@Transactional
	public void esegui(OperazioneAttrezzatura operazione) throws Exception {
            Cantiere cantiere = getCantiere(operazione);
            Operatore operatore = getOperatore(operazione);
            Attrezzatura operabile = getOperabile(operazione);

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
		log.info("esegui: {}, {}, {}",operazione.getTipoOperazione(),operazione.getCantiere().getId(),operabile.getId(),operazione.getOperatore().getId());
		operabileDao.save(operabile);
		operazioneDao.save(operazione);	
		super.sendToKafka(new KafkaOperazione(operazione, operabile,cantiere, operatore));
	}
}
