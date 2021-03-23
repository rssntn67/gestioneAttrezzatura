package it.arsinfo.ga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.arsinfo.ga.dao.OperatoreDao;
import it.arsinfo.ga.model.data.StatoOperatore;
import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.service.OperatoreService;

@Service
public class OperatoreServiceDaoImpl implements OperatoreService {

    @Autowired
    private OperatoreDao repository;
    
	@Override
	public Operatore save(Operatore entity) throws Exception {
		return repository.save(entity);
	}

	@Override
	public void delete(Operatore entity) throws Exception {
		repository.delete(entity);
	}

	@Override
	public Operatore findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Operatore> findAll() {
		return repository.findAll();
	}

	public OperatoreDao getRepository() {
		return repository;
	}

	@Override
	public List<Operatore> searchBy(String search, StatoOperatore stato) {
		if (!StringUtils.hasLength(search) && stato == null) 
			return repository.findAll();
		if (!StringUtils.hasLength(search))
			return repository.findByStato(stato);
		if (stato == null)
			return repository.findByIdentificativoContainingIgnoreCase(search);
		return repository.findByIdentificativoContainingIgnoreCaseAndStato(search, stato);
	}

	@Override
	public Operatore findByIdentificativo(String identificatico) {
		return repository.findByIdentificativo(identificatico);
	}

	@Override
	public Operatore findByApikey(String apikey) {
		return repository.findByApikey(apikey);
	}	
}
