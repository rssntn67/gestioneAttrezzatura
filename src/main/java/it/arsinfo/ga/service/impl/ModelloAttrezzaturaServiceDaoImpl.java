package it.arsinfo.ga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.arsinfo.ga.dao.ModelloAttrezzaturaDao;
import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.MarcaAttrezzatura;
import it.arsinfo.ga.model.data.TipoAttrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.service.ModelloAttrezzaturaService;

@Service
public class ModelloAttrezzaturaServiceDaoImpl implements ModelloAttrezzaturaService {

    @Autowired
    private ModelloAttrezzaturaDao repository;

	@Override
	public ModelloAttrezzatura save(ModelloAttrezzatura entity) throws Exception {
		return repository.save(entity);
	}

	@Override
	public void delete(ModelloAttrezzatura entity) throws Exception {
		repository.delete(entity);
	}

	@Override
	public ModelloAttrezzatura findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<ModelloAttrezzatura> findAll() {
		return repository.findAll();
	}

	public ModelloAttrezzaturaDao getRepository() {
		return repository;
	}

	@Override
	public List<ModelloAttrezzatura> searchBy(Fornitore fornitore, Anno anno, String nome,
			TipoAttrezzatura tipo, MarcaAttrezzatura marca) {
		if (fornitore == null && anno == null && !StringUtils.hasLength(nome) && tipo == null && marca == null)
			return repository.findAll();
		
		if (fornitore == null && !StringUtils.hasLength(nome) && tipo == null && marca == null)
			return repository.findByAnnoProduzione(anno);
		if (fornitore == null && anno == null && tipo == null && marca == null)
			return repository.findByNomeContainingIgnoreCase(nome);
		if (fornitore == null && anno == null && !StringUtils.hasLength(nome) && marca == null)
			return repository.findByTipo(tipo);
		if (fornitore == null && anno == null && !StringUtils.hasLength(nome) && tipo == null)
			return repository.findByMarca(marca);
		if (anno == null && !StringUtils.hasLength(nome) && tipo == null && marca == null)
			return repository.findByFornitore(fornitore);
		
		if (fornitore == null && tipo == null && marca == null)
			return repository.findByNomeContainingIgnoreCaseAndAnnoProduzione(nome, anno);
		if (fornitore == null && anno == null && marca == null)
			return repository.findByNomeContainingIgnoreCaseAndTipo(nome, tipo);
		if (fornitore == null && anno == null && tipo == null)
			return repository.findByNomeContainingIgnoreCaseAndMarca(nome, marca);	
		if (fornitore == null && !StringUtils.hasLength(nome) && marca == null)
			return repository.findByAnnoProduzioneAndTipo(anno,tipo);
		if (fornitore == null && !StringUtils.hasLength(nome) && tipo == null)
			return repository.findByAnnoProduzioneAndMarca(anno,marca);
		if (fornitore == null && anno == null && !StringUtils.hasLength(nome))
			return repository.findByMarcaAndTipo(marca, tipo);
		if (!StringUtils.hasLength(nome) && tipo == null && marca == null)
			return repository.findByFornitoreAndAnnoProduzione(fornitore,anno);
		if (anno == null && tipo == null && marca == null)
			return repository.findByNomeContainingIgnoreCaseAndFornitore(nome,fornitore);
		if (anno == null && !StringUtils.hasLength(nome) && marca == null)
			return repository.findByFornitoreAndTipo(fornitore,tipo);
		if (anno == null && !StringUtils.hasLength(nome) && tipo == null)
			return repository.findByFornitoreAndMarca(fornitore,marca);
		
		if (tipo == null && marca == null)
			return repository.findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzione(nome, fornitore,anno);
		if (anno == null && tipo == null)
			return repository.findByNomeContainingIgnoreCaseAndFornitoreAndMarca(nome, fornitore,marca);
		if (anno == null && marca == null)
			return repository.findByNomeContainingIgnoreCaseAndFornitoreAndTipo(nome, fornitore,tipo);
		if (!StringUtils.hasLength(nome) && marca == null)
			return repository.findByFornitoreAndAnnoProduzioneAndTipo(fornitore,anno,tipo);
		if (!StringUtils.hasLength(nome) && tipo == null)
			return repository.findByFornitoreAndAnnoProduzioneAndMarca(fornitore,anno,marca);
		if (!StringUtils.hasLength(nome) && anno == null)
			return repository.findByFornitoreAndTipoAndMarca(fornitore,tipo,marca);
		if (fornitore == null && marca == null)
			return repository.findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipo(nome, anno, tipo);
		if (fornitore == null && tipo == null)
			return repository.findByNomeContainingIgnoreCaseAndAnnoProduzioneAndMarca(nome, anno, marca);
		if (fornitore == null && anno == null)
			return repository.findByNomeContainingIgnoreCaseAndTipoAndMarca(nome, tipo, marca);
		if (fornitore == null && !StringUtils.hasLength(nome))
			return repository.findByAnnoProduzioneAndTipoAndMarca(anno, tipo, marca);
		

		if (!StringUtils.hasLength(nome))
			return repository.findByFornitoreAndAnnoProduzioneAndTipoAndMarca(fornitore, anno, tipo, marca);
		if (fornitore == null)
			return repository.findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipoAndMarca(nome, anno, tipo, marca);
		if (anno == null)
			return repository.findByNomeContainingIgnoreCaseAndFornitoreAndTipoAndMarca(nome, fornitore, tipo, marca);
		if (tipo ==null)
			return repository.findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzioneAndMarca(nome, fornitore, anno, marca);
		if (marca ==null)
			return repository.findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzioneAndTipo(nome, fornitore, anno, tipo);
		
		
		return repository.findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzioneAndTipoAndMarca(nome, fornitore,anno, tipo, marca);
	}

	@Override
	public List<ModelloAttrezzatura> findByNomeAndFornitoreAndAnnoProduzione(String nome, Fornitore fornitore, Anno anno) {
		return repository.findByNomeAndFornitoreAndAnnoProduzione(nome, fornitore, anno);
	}

	
}
