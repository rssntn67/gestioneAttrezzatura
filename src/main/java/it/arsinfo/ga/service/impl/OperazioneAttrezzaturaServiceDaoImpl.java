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

import it.arsinfo.ga.dao.AttrezzaturaDao;
import it.arsinfo.ga.dao.CantiereDao;
import it.arsinfo.ga.dao.OperazioneAttrezzaturaDao;
import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.service.OperazioneService;

@Service
public class OperazioneAttrezzaturaServiceDaoImpl implements OperazioneService<Attrezzatura, OperazioneAttrezzatura> {
	
	@Autowired
	private AttrezzaturaDao operabileDao;

	@Autowired
	private CantiereDao cantiereDao;

	@Autowired
	private OperazioneAttrezzaturaDao operazioneDao;
	
    private static final Logger log = LoggerFactory.getLogger(OperazioneAttrezzaturaServiceDaoImpl.class);

	@Override
	@Transactional
	public void esegui(OperazioneAttrezzatura operazione) throws Exception {
		if (operazione.getTipoOperazione() == null)
			throw new UnsupportedOperationException("TipoOperazione non può essere null");
		if (operazione.getOperabile() == null)
			throw new UnsupportedOperationException("Operabile non può essere null");
		if (operazione.getCantiere() == null )
			throw new UnsupportedOperationException("Cantiere non può essere null");
		Attrezzatura operabile = operabileDao.findById(operazione.getOperabile().getId()).get();
		Cantiere cantiere = cantiereDao.findById(operazione.getCantiere().getId()).get();
		if (cantiere == null || cantiere.getStato() != StatoCantiere.InOpera) {
			throw new UnsupportedOperationException("Stato Cantiere non operabile: " + cantiere.getStato());			
		}

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
		log.info("esegui: {}, {}, {}",operazione.getTipoOperazione(),cantiere,operabile);
		operabileDao.save(operabile);
		operazioneDao.save(operazione);						
	}

	@Override
	public List<Cantiere> getCantieri() {
		return cantiereDao.findByStato(StatoCantiere.InOpera);
	}

	@Override
	public List<Attrezzatura> getOperabili() {
		return operabileDao.findByStatoNotOrStatoNot(StatoOperabile.Dismesso,StatoOperabile.InRiparazione);
	}

	@Override
	public List<OperazioneAttrezzatura> findAll() {
		return populate(operazioneDao.findAll());
	}

	@Override
	public List<OperazioneAttrezzatura> searchBy(Cantiere cantiere, Attrezzatura operabile, TipoOperazione t) {
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
	public List<OperazioneAttrezzatura> populate(List<OperazioneAttrezzatura> operazioni) {
		Map<Long,Cantiere> cmap = cantiereDao.findAll().stream().collect(Collectors.toMap(Cantiere::getId, Function.identity()));
		Map<Long,Attrezzatura> omap = operabileDao.findAll().stream().collect(Collectors.toMap(Attrezzatura::getId, Function.identity()));
		List<OperazioneAttrezzatura> list = new ArrayList<OperazioneAttrezzatura>();
		for (OperazioneAttrezzatura o: operazioni) {
			o.setCantiere(cmap.get(o.getCantiere().getId()));
			o.setOperabile(omap.get(o.getOperabile().getId()));
			list.add(o);
		}
		return list;
	}

	@Override
	public OperazioneAttrezzatura getLastOperation(Attrezzatura operabile) {
		return operazioneDao.findTopByOperabileOrderByIdDesc(operabile);
	}
}
