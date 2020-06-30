package it.arsinfo.ga.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.arsinfo.ga.dao.ModelloAttrezzaturaServiceDao;
import it.arsinfo.ga.dao.repository.ModelloAttrezzaturaDao;
import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.MarcaModello;
import it.arsinfo.ga.data.TipoModello;
import it.arsinfo.ga.entity.ModelloAttrezzatura;

@Service
public class ModelloAttrezzaturaServiceDaoImpl implements ModelloAttrezzaturaServiceDao {

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
	public List<ModelloAttrezzatura> searchBy(Anno searchAnnoProduzione, String searchNome,
			TipoModello searchTipoModello, MarcaModello searchMarcaModello) {
		if (searchAnnoProduzione == null && StringUtils.isEmpty(searchNome) && searchTipoModello == null && searchMarcaModello == null)
			return repository.findAll();
		
		if (StringUtils.isEmpty(searchNome) && searchTipoModello == null && searchMarcaModello == null)
			return repository.findByAnnoProduzione(searchAnnoProduzione);
		if (searchAnnoProduzione == null && searchTipoModello == null && searchMarcaModello == null)
			return repository.findByNomeContainingIgnoreCase(searchNome);
		if (searchAnnoProduzione == null && StringUtils.isEmpty(searchNome) && searchMarcaModello == null)
			return repository.findByTipoModello(searchTipoModello);
		if (searchAnnoProduzione == null && StringUtils.isEmpty(searchNome) && searchTipoModello == null)
			return repository.findByMarcaModello(searchMarcaModello);
		
		if (searchTipoModello == null && searchMarcaModello == null)
			return repository.findByNomeContainingIgnoreCaseAndAnnoProduzione(searchNome, searchAnnoProduzione);
		if (searchAnnoProduzione == null && searchMarcaModello == null)
			return repository.findByNomeContainingIgnoreCaseAndTipoModello(searchNome, searchTipoModello);
		if (searchAnnoProduzione == null && searchTipoModello == null)
			return repository.findByNomeContainingIgnoreCaseAndMarcaModello(searchNome, searchMarcaModello);	
		if (StringUtils.isEmpty(searchNome) && searchMarcaModello == null)
			return repository.findByAnnoProduzioneAndTipoModello(searchAnnoProduzione,searchTipoModello);
		if (StringUtils.isEmpty(searchNome) && searchTipoModello == null)
			return repository.findByAnnoProduzioneAndMarcaModello(searchAnnoProduzione,searchMarcaModello);
		if (searchAnnoProduzione == null && StringUtils.isEmpty(searchNome))
			return repository.findByMarcaModelloAndTipoModello(searchMarcaModello, searchTipoModello);
		
		if (searchMarcaModello == null)
			return repository.findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipoModello(searchNome, searchAnnoProduzione, searchTipoModello);
		if (searchTipoModello == null)
			return repository.findByNomeContainingIgnoreCaseAndAnnoProduzioneAndMarcaModello(searchNome, searchAnnoProduzione, searchMarcaModello);
		if (searchAnnoProduzione == null)
			return repository.findByNomeContainingIgnoreCaseAndTipoModelloAndMarcaModello(searchNome, searchTipoModello, searchMarcaModello);
		if (StringUtils.isEmpty(searchNome))
			return repository.findByAnnoProduzioneAndTipoModelloAndMarcaModello(searchAnnoProduzione, searchTipoModello, searchMarcaModello);
		
		return repository.findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipoModelloAndMarcaModello(searchNome, searchAnnoProduzione, searchTipoModello, searchMarcaModello);
	}

	
}
