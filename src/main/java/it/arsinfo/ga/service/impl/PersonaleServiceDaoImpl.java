package it.arsinfo.ga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.arsinfo.ga.dao.ModelloPersonaleDao;
import it.arsinfo.ga.dao.PersonaleDao;
import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.service.PersonaleService;

@Service
public class PersonaleServiceDaoImpl implements PersonaleService {

    @Autowired
    private PersonaleDao repository;

    @Autowired
    private ModelloPersonaleDao modelliDao;

	@Override
	public Personale save(Personale entity) throws Exception {
		return repository.save(entity);
	}

	@Override
	public void delete(Personale entity) throws Exception {
		repository.delete(entity);
	}

	@Override
	public Personale findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Personale> findAll() {
		return repository.findAll();
	}

	public PersonaleDao getRepository() {
		return repository;
	}

	@Override
	public List<Personale> searchBy(StatoOperabile searchStatoPersonale, String searchIdentificativo,
			ModelloPersonale searchModello) {
		if (StringUtils.isEmpty(searchIdentificativo) && searchStatoPersonale == null && searchModello == null)
			return repository.findAll();

		if (StringUtils.isEmpty(searchIdentificativo) && searchStatoPersonale == null )
			return repository.findByModello(searchModello);

		if (StringUtils.isEmpty(searchIdentificativo) && searchModello == null)
			return repository.findByStato(searchStatoPersonale);

		if (searchStatoPersonale == null && searchModello == null)
			return repository.findByIdentificativoContainingIgnoreCase(searchIdentificativo);

		if ( searchModello == null)
			return repository.findByIdentificativoContainingIgnoreCaseAndStato(searchIdentificativo, searchStatoPersonale);

		if (searchStatoPersonale == null )
			return repository.findByIdentificativoContainingIgnoreCaseAndModello(searchIdentificativo, searchModello);

		if (StringUtils.isEmpty(searchIdentificativo))
			return repository.findByStatoAndModello(searchStatoPersonale, searchModello);

		return repository.findByIdentificativoContainingIgnoreCaseAndStatoAndModello(searchIdentificativo, searchStatoPersonale, searchModello);
	}

	@Override
	public List<ModelloPersonale> getModelli() {
		return modelliDao.findAll();
	}

	@Override
	public Personale findByIdentificativo(String identificatico) {
		return repository.findByIdentificativo(identificatico);
	}

	
}
