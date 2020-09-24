package it.arsinfo.ga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.arsinfo.ga.dao.AttrezzaturaDao;
import it.arsinfo.ga.dao.ModelloAttrezzaturaDao;
import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.service.AttrezzaturaService;

@Service
public class AttrezzaturaServiceDaoImpl implements AttrezzaturaService {

    @Autowired
    private AttrezzaturaDao repository;

    @Autowired
    private ModelloAttrezzaturaDao modelliDao;

    @Autowired
    private OperazioneAttrezzaturaServiceDaoImpl service;
	@Override
	public Attrezzatura save(Attrezzatura entity) throws Exception {
		return repository.save(entity);
	}

	@Override
	public void delete(Attrezzatura entity) throws Exception {
		repository.delete(entity);
	}

	@Override
	public Attrezzatura findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Attrezzatura> findAll() {
		return repository.findAll();
	}

	public AttrezzaturaDao getRepository() {
		return repository;
	}

	@Override
	public List<Attrezzatura> searchBy(StatoOperabile searchStatoAttrezzatura, String searchIdentificativo,
			ModelloAttrezzatura searchModello) {
		if (StringUtils.isEmpty(searchIdentificativo) && searchStatoAttrezzatura == null && searchModello == null)
			return repository.findAll();

		if (StringUtils.isEmpty(searchIdentificativo) && searchStatoAttrezzatura == null )
			return repository.findByModello(searchModello);

		if (StringUtils.isEmpty(searchIdentificativo) && searchModello == null)
			return repository.findByStato(searchStatoAttrezzatura);

		if (searchStatoAttrezzatura == null && searchModello == null)
			return repository.findByIdentificativoContainingIgnoreCase(searchIdentificativo);

		if ( searchModello == null)
			return repository.findByIdentificativoContainingIgnoreCaseAndStato(searchIdentificativo, searchStatoAttrezzatura);

		if (searchStatoAttrezzatura == null )
			return repository.findByIdentificativoContainingIgnoreCaseAndModello(searchIdentificativo, searchModello);

		if (StringUtils.isEmpty(searchIdentificativo))
			return repository.findByStatoAndModello(searchStatoAttrezzatura, searchModello);

		return repository.findByIdentificativoContainingIgnoreCaseAndStatoAndModello(searchIdentificativo, searchStatoAttrezzatura, searchModello);
	}

	@Override
	public List<ModelloAttrezzatura> getModelli() {
		return modelliDao.findAll();
	}

	@Override
	public Attrezzatura findByIdentificativo(String identificatico) {
		return repository.findByIdentificativo(identificatico);
	}

	@Override
	public List<OperazioneAttrezzatura> findOperazioni(Attrezzatura t) {
		return service.searchBy(null, t,null);
	}

	
}
