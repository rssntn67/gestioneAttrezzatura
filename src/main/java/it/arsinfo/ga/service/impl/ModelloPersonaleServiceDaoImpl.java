package it.arsinfo.ga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.arsinfo.ga.dao.ModelloPersonaleDao;
import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.TipoPersonale;
import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.service.ModelloPersonaleService;

@Service
public class ModelloPersonaleServiceDaoImpl implements ModelloPersonaleService {

    @Autowired
    private ModelloPersonaleDao repository;

	@Override
	public ModelloPersonale save(ModelloPersonale entity) throws Exception {
		return repository.save(entity);
	}

	@Override
	public void delete(ModelloPersonale entity) throws Exception {
		repository.delete(entity);
	}

	@Override
	public ModelloPersonale findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<ModelloPersonale> findAll() {
		return repository.findAll();
	}

	public ModelloPersonaleDao getRepository() {
		return repository;
	}

	@Override
	public List<ModelloPersonale> searchBy(Fornitore fornitore, Anno anno, String nome,
			TipoPersonale tipo) {
		if (fornitore == null && anno == null && !StringUtils.hasLength(nome) && tipo == null)
			return repository.findAll();
		
		if (fornitore == null && !StringUtils.hasLength(nome) && tipo == null)
			return repository.findByAnnoProduzione(anno);
		if (fornitore == null && anno == null && tipo == null)
			return repository.findByNomeContainingIgnoreCase(nome);
		if (fornitore == null && anno == null && !StringUtils.hasLength(nome))
			return repository.findByTipo(tipo);
		if (anno == null && !StringUtils.hasLength(nome) && tipo == null)
			return repository.findByFornitore(fornitore);
		
		if (fornitore == null && tipo == null )
			return repository.findByNomeContainingIgnoreCaseAndAnnoProduzione(nome, anno);
		if (fornitore == null && anno == null )
			return repository.findByNomeContainingIgnoreCaseAndTipo(nome, tipo);
		if (fornitore == null && !StringUtils.hasLength(nome))
			return repository.findByAnnoProduzioneAndTipo(anno,tipo);
		if (!StringUtils.hasLength(nome) && tipo == null)
			return repository.findByFornitoreAndAnnoProduzione(fornitore,anno);
		if (anno == null && tipo == null)
			return repository.findByNomeContainingIgnoreCaseAndFornitore(nome,fornitore);
		if (anno == null && !StringUtils.hasLength(nome))
			return repository.findByFornitoreAndTipo(fornitore,tipo);
		
		if (tipo == null)
			return repository.findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzione(nome, fornitore,anno);
		if (anno == null)
			return repository.findByNomeContainingIgnoreCaseAndFornitoreAndTipo(nome, fornitore,tipo);
		if (!StringUtils.hasLength(nome))
			return repository.findByFornitoreAndAnnoProduzioneAndTipo(fornitore,anno,tipo);
		if (fornitore == null )
			return repository.findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipo(nome, anno, tipo);
		

		return repository.findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzioneAndTipo(nome, fornitore, anno, tipo);		
	}

	@Override
	public List<ModelloPersonale> findByNomeAndFornitoreAndAnnoProduzione(String nome, Fornitore fornitore, Anno anno) {
		return repository.findByNomeAndFornitoreAndAnnoProduzione(nome, fornitore, anno);
	}

	
}
