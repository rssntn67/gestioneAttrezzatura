package it.arsinfo.ga.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.arsinfo.ga.dao.CantiereDao;
import it.arsinfo.ga.dao.OperazionePersonaleDao;
import it.arsinfo.ga.dao.PersonaleDao;
import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.model.entity.OperazionePersonale;
import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.service.OperazioneService;

@Service
public class OperazionePersonaleService implements OperazioneService<ModelloPersonale, Personale, OperazionePersonale> {
	
	@Autowired
	private PersonaleDao operabileDao;

	@Autowired
	private CantiereDao cantiereDao;

	@Autowired
	private OperazionePersonaleDao operazioneDao;
	
    private static final Logger log = LoggerFactory.getLogger(OperazionePersonaleService.class);

	@Override
	@Transactional
	public void esegui(OperazionePersonale operazione) throws Exception {
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
		Personale operabile = operabileDao.findById(operazione.getOperabile().getId()).get();
		log.info("esegui: {}, {}, {}",operazione.getTipoOperazione(),cantiere,operabile);
		switch (operazione.getTipoOperazione()) {
			case Carico:
				if (operazione.getNumero() > operabile.getDisponibili()) {
					throw new UnsupportedOperationException("Numero operabile minore disponibile");
				}
				operabile.setUtilizzati(operabile.getUtilizzati()+operazione.getNumero());
				if (operabile.getDisponibili() == 0) {
					operabile.setStato(StatoOperabile.Occupato);
				}
				break;
			case Scarico:
				if (operazione.getNumero() > operabile.getUtilizzati()) {
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
		operabileDao.save(operabile);
		operazioneDao.save(operazione);						
	}

	@Override
	public List<Cantiere> getCantieri() {
		return cantiereDao.findByStato(StatoCantiere.InOpera);
	}

	@Override
	public List<Personale> getOperabili() {
		return operabileDao.findByStato(StatoOperabile.Disponibile);
	}
	
	@Override
	public List<OperazionePersonale> findAll() {
		return populate(operazioneDao.findAll());
	}

	@Override
	public List<OperazionePersonale> searchBy(Cantiere cantiere, Personale operabile, TipoOperazione t) {
		if (cantiere == null && operabile == null && t == null)
			return populate(operazioneDao.findAll());
		if (cantiere == null && operabile == null)
			return populate(operazioneDao.findByTipoOperazione(t));
		if (cantiere == null && t == null)
			return populate(operazioneDao.findByOperabile(operabile));
		if (operabile == null && t == null)
			return populate(operazioneDao.findByCantiere(cantiere));
		if (cantiere == null) 
			return populate(operazioneDao.findByOperabileAndTipoOperazione(operabile, t));
		if (operabile == null)
			return populate(operazioneDao.findByCantiereAndTipoOperazione(cantiere, t));
		if (t == null)
			return populate(operazioneDao.findByCantiereAndOperabile(cantiere, operabile));
		return populate(operazioneDao.findByCantiereAndOperabileAndTipoOperazione(cantiere, operabile, t));
	}
	
	@Override
	public List<OperazionePersonale> populate(List<OperazionePersonale> operazioni) {
		Map<Long,Cantiere> cmap = cantiereDao.findAll().stream().collect(Collectors.toMap(Cantiere::getId, Function.identity()));
		Map<Long,Personale> omap = operabileDao.findAll().stream().collect(Collectors.toMap(Personale::getId, Function.identity()));
		List<OperazionePersonale> list = new ArrayList<OperazionePersonale>();
		for (OperazionePersonale o: operazioni) {
			o.setCantiere(cmap.get(o.getCantiere().getId()));
			o.setOperabile(omap.get(o.getOperabile().getId()));
			list.add(o);
		}
		return list;
	}

}
