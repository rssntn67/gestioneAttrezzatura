package it.arsinfo.ga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.arsinfo.ga.dao.CantiereDao;
import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.TipoCantiere;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.model.entity.OperazioneConsumabile;
import it.arsinfo.ga.model.entity.OperazionePersonale;
import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.service.CantiereService;
import it.arsinfo.ga.service.OperazioneService;

@Service
public class CantiereServiceDaoImpl implements CantiereService {

    @Autowired
    private CantiereDao repository;
    
    @Autowired
    private OperazioneService<Attrezzatura, OperazioneAttrezzatura> operAttrService;
    
    @Autowired 
    private OperazioneService<Consumabile,OperazioneConsumabile> operConsService;
    
    @Autowired
    private OperazioneService<Personale, OperazionePersonale> operPersService;

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
	public List<Cantiere> searchBy(String search, StatoCantiere stato, TipoCantiere tipo) {
		if (!StringUtils.hasLength(search) && stato == null && tipo == null) 
			return repository.findAll();
		if (!StringUtils.hasLength(search) && tipo == null)
			return repository.findByStato(stato);
		if (!StringUtils.hasLength(search) && stato == null)
			return repository.findByTipo(tipo);
		if (stato == null && tipo == null)
			return repository.findByIdentificativoContainingIgnoreCase(search);
		if (!StringUtils.hasLength(search))
			return repository.findByStatoAndTipo(stato, tipo);
		if (stato == null)
			return repository.findByIdentificativoContainingIgnoreCaseAndTipo(search, tipo);
		if (tipo == null)
			return repository.findByIdentificativoContainingIgnoreCaseAndStato(search, stato);
		return repository.findByIdentificativoContainingIgnoreCaseAndStatoAndTipo(search, stato, tipo);
	}

	@Override
	public Cantiere findByIdentificativo(String identificatico) {
		return repository.findByIdentificativo(identificatico);
	}

	@Override
	public List<OperazioneAttrezzatura> findOperazioneAttrezzatura(Cantiere cantiere) {
		return operAttrService.searchBy(cantiere, null, null,null);
	}

	@Override
	public List<OperazioneConsumabile> findOperazioneConsumabile(Cantiere cantiere) {
		return operConsService.searchBy(cantiere, null, null,null);
	}

	@Override
	public List<OperazionePersonale> findOperazionePersonale(Cantiere cantiere) {
		return operPersService.searchBy(cantiere, null, null,null);
	}

	
}
