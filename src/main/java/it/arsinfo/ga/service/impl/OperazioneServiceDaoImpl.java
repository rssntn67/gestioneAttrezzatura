package it.arsinfo.ga.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.arsinfo.ga.dao.CantiereDao;
import it.arsinfo.ga.dao.OperabileDao;
import it.arsinfo.ga.dao.OperatoreDao;
import it.arsinfo.ga.dao.OperazioneDao;
import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.data.StatoOperatore;
import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Modello;
import it.arsinfo.ga.model.entity.Operabile;
import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.model.entity.Operazione;
import it.arsinfo.ga.service.OperazioneService;

public abstract class OperazioneServiceDaoImpl<M extends Modello,T extends Operabile<M>,S extends Operazione<T>> 
implements OperazioneService<T,S>{
	
    private static final Logger log = LoggerFactory.getLogger(OperazioneServiceDaoImpl.class);


	@Autowired
	private CantiereDao cantiereDao;

	@Autowired
	private OperatoreDao operatoreDao;
		
	@Autowired
	private OperabileDao<M,T> operabileDao;
	
	@Autowired
	private OperazioneDao<T, S> operazioneDao;
	
	@Override
	public List<Cantiere> getCantieri() {
		return cantiereDao.findByStato(StatoCantiere.InOpera);
	}

	@Override
	public List<Operatore> getOperatori() {
		return operatoreDao.findByStato(StatoOperatore.Attivo);
	}
	
	@Override
	public List<T> getOperabili() {
		return operabileDao.findByStato(StatoOperabile.Disponibile);
	}

	@Override
	public List<S> findAll() {
		return populate(operazioneDao.findAll());
	}

	protected T check(S s) throws Exception {
		if (s.getCantiere().getStato() != StatoCantiere.InOpera) {
			log.error("Stato Cantiere non In Opera: {}", s.getCantiere());
			throw new UnsupportedOperationException("Stato Cantiere non InOpera: " + s.getCantiere().getStato());			
		}
		if (s.getOperatore().getStato() != StatoOperatore.Attivo) {
			log.error("Stato Operatore non Attivo: {}", s.getOperatore());
			throw new UnsupportedOperationException("Stato Operatore non Attivo: " + s.getOperatore().getStato());			
			
		}
		return operabileDao.findById(s.getOperabile().getId()).get();

	}
	public List<S> populate(List<S> operazioni) {
		Map<Long,Cantiere> cmap = cantiereDao.findAll().stream().collect(Collectors.toMap(Cantiere::getId, Function.identity()));
		Map<Long,T> omap = operabileDao.findAll().stream().collect(Collectors.toMap(T::getId, Function.identity()));
		Map<Long,Operatore> oomap = operatoreDao.findAll().stream().collect(Collectors.toMap(Operatore::getId, Function.identity()));
		List<S> list = new ArrayList<S>();
		for (S o: operazioni) {
			o.setCantiere(cmap.get(o.getCantiere().getId()));
			o.setOperabile(omap.get(o.getOperabile().getId()));
			o.setOperatore(oomap.get(o.getOperatore().getId()));
			list.add(o);
		}
		return list;
	}
	
	@Override
	public List<S> searchBy(Cantiere c, T t, TipoOperazione tipo, Operatore o) {
		if (c == null && t == null && tipo == null && o == null)
			return populate(operazioneDao.findAll());
		
		if (c == null && t == null && o == null)
			return populate(operazioneDao.findByTipoOperazione(tipo));
		if (c == null && tipo == null && o == null)
			return populate(operazioneDao.findByOperabile(t));
		if (t == null && tipo == null && o == null)
			return populate(operazioneDao.findByCantiere(c));
		if (c == null && t == null && tipo == null)
			return populate(operazioneDao.findByOperatore(o));

		if (c == null && o == null) 
			return populate(operazioneDao.findByOperabileAndTipoOperazione(t, tipo));
		if (t == null && o == null)
			return populate(operazioneDao.findByCantiereAndTipoOperazione(c, tipo));
		if (tipo == null && o == null)
			return populate(operazioneDao.findByCantiereAndOperabile(c, t));
		
		if (c == null && t == null) 
			return populate(operazioneDao.findByOperatoreAndTipoOperazione(o, tipo));
		if (c == null && tipo == null) 
			return populate(operazioneDao.findByOperabileAndOperatore(t, o));
		if (t == null && tipo == null) 
			return populate(operazioneDao.findByCantiereAndOperatore(c, o));
		
		if (o == null)
			return populate(operazioneDao.findByCantiereAndOperabileAndTipoOperazione(c, t, tipo));
		if (tipo == null)
			return populate(operazioneDao.findByCantiereAndOperabileAndOperatore(c, t, o));
		if (t == null)
			return populate(operazioneDao.findByCantiereAndOperatoreAndTipoOperazione(c, o, tipo));
		if (c == null)
			return populate(operazioneDao.findByOperabileAndOperatoreAndTipoOperazione(t, o, tipo));

		return populate(operazioneDao.findByCantiereAndOperabileAndOperatoreAndTipoOperazione(c, t, o, tipo));
	}

	@Override
	public S getLastOperation(T operabile) {
		return operazioneDao.findTopByOperabileOrderByIdDesc(operabile);
	}

}
