package it.arsinfo.ga.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.arsinfo.ga.dao.AttrezzaturaDao;
import it.arsinfo.ga.dao.OperazioneAttrezzaturaDao;
import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.service.OperazioneService;

@Service
public class OperazioneAttrezzaturaService implements OperazioneService<ModelloAttrezzatura, Attrezzatura> {
	
	@Autowired
	private AttrezzaturaDao operabileDao;
	
	@Autowired
	private OperazioneAttrezzaturaDao operazioneDao;
	
    private static final Logger log = LoggerFactory.getLogger(OperazioneAttrezzaturaService.class);

	@Override
	public void esegui(TipoOperazione tipo, Cantiere cantiere, Attrezzatura operabile, int numero) throws UnsupportedOperationException {
		log.info("esegui: {}, {}, {}, {}",tipo,cantiere,operabile,numero);
		if (numero != 1) 
			throw new UnsupportedOperationException("Non posso Caricare Operabile al Cantiere numero deve essere 1:" + numero);
		switch (tipo) {
			case Carico:
				carico(cantiere,operabile);
				break;
			case Scarico:
				scarico(cantiere,operabile);
				break;
			case Furto:
				furto(cantiere,operabile);
				break;
			case Rottura:
				rottura(cantiere,operabile);
				break;
			case Smarrimento:
				smarrimento(cantiere,operabile);
				break;
	
			default:
				break;
			}		
	}

	@Transactional
	private void smarrimento(Cantiere cantiere, Attrezzatura operabile) throws UnsupportedOperationException {
		operabile.setStato(StatoOperabile.Dismesso);
		OperazioneAttrezzatura operazione = new OperazioneAttrezzatura();
		operazione.setCantiere(cantiere);
		operazione.setOperabile(operabile);
		operazione.setTipoOperazione(TipoOperazione.Smarrimento);
		operabileDao.save(operabile);
		operazioneDao.save(operazione);						
	}

	@Transactional
	private void rottura(Cantiere cantiere, Attrezzatura operabile) throws UnsupportedOperationException  {
		operabile.setStato(StatoOperabile.Dismesso);
		OperazioneAttrezzatura operazione = new OperazioneAttrezzatura();
		operazione.setCantiere(cantiere);
		operazione.setOperabile(operabile);
		operazione.setTipoOperazione(TipoOperazione.Rottura);
		operabileDao.save(operabile);
		operazioneDao.save(operazione);				
	}

	@Transactional
	private void furto(Cantiere cantiere, Attrezzatura operabile) throws UnsupportedOperationException {
		operabile.setStato(StatoOperabile.Dismesso);
		OperazioneAttrezzatura operazione = new OperazioneAttrezzatura();
		operazione.setCantiere(cantiere);
		operazione.setOperabile(operabile);
		operazione.setTipoOperazione(TipoOperazione.Furto);
		operabileDao.save(operabile);
		operazioneDao.save(operazione);		
		
	}

	@Transactional
	private void scarico(Cantiere cantiere, Attrezzatura operabile) throws UnsupportedOperationException  {
		operabile.setStato(StatoOperabile.Disponibile);
		OperazioneAttrezzatura operazione = new OperazioneAttrezzatura();
		operazione.setCantiere(cantiere);
		operazione.setOperabile(operabile);
		operazione.setTipoOperazione(TipoOperazione.Scarico);
		operabileDao.save(operabile);
		operazioneDao.save(operazione);		
	}

	@Transactional
	private void carico(Cantiere cantiere, Attrezzatura operabile) throws UnsupportedOperationException {
		operabile.setStato(StatoOperabile.Occupato);
		OperazioneAttrezzatura operazione = new OperazioneAttrezzatura();
		operazione.setCantiere(cantiere);
		operazione.setOperabile(operabile);
		operazione.setTipoOperazione(TipoOperazione.Carico);
		operabileDao.save(operabile);
		operazioneDao.save(operazione);
	}

}
