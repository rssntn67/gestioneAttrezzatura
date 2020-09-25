package it.arsinfo.ga.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
	public List<S> searchBy(Cantiere cantiere, T operabile, TipoOperazione t, Operatore o) {
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
	public S getLastOperation(T operabile) {
		return operazioneDao.findTopByOperabileOrderByIdDesc(operabile);
	}

}
