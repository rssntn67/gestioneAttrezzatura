package it.arsinfo.ga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.arsinfo.ga.dao.CantiereDao;
import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.service.CantiereService;

@Service
public class CantiereServiceDaoImpl implements CantiereService {

    @Autowired
    private CantiereDao repository;

	@Override
	public Cantiere save(Cantiere entity) throws Exception {
		return repository.save(entity);
	}

	@Override
	public void delete(Cantiere entity) throws Exception {
		repository.delete(entity);
	}

	@Override
	public Cantiere findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Cantiere> findAll() {
		return repository.findAll();
	}

	public CantiereDao getRepository() {
		return repository;
	}

	@Override
	public List<Cantiere> searchBy(String searchIdentificativo, StatoCantiere statoCantiere) {
		if (StringUtils.isEmpty(searchIdentificativo) && statoCantiere == null) 
			return repository.findAll();
		if (StringUtils.isEmpty(searchIdentificativo))
			return repository.findByStatoCantiere(statoCantiere);
		if (statoCantiere == null) 
			return repository.findByIdentificativoContainingIgnoreCase(searchIdentificativo);
		
		return repository.findByIdentificativoContainingIgnoreCaseAndStatoCantiere(searchIdentificativo, statoCantiere);
	}

	
}
