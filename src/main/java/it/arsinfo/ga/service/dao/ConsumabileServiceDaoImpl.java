package it.arsinfo.ga.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.arsinfo.ga.dao.ConsumabileServiceDao;
import it.arsinfo.ga.dao.repository.ConsumabileDao;
import it.arsinfo.ga.dao.repository.ModelloConsumabileDao;
import it.arsinfo.ga.data.StatoOperabile;
import it.arsinfo.ga.entity.Consumabile;
import it.arsinfo.ga.entity.ModelloConsumabile;

@Service
public class ConsumabileServiceDaoImpl implements ConsumabileServiceDao {

    @Autowired
    private ConsumabileDao repository;

    @Autowired
    private ModelloConsumabileDao modelliDao;

	@Override
	public Consumabile save(Consumabile entity) throws Exception {
		return repository.save(entity);
	}

	@Override
	public void delete(Consumabile entity) throws Exception {
		repository.delete(entity);
	}

	@Override
	public Consumabile findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Consumabile> findAll() {
		return repository.findAll();
	}

	public ConsumabileDao getRepository() {
		return repository;
	}

	@Override
	public List<Consumabile> searchBy(StatoOperabile searchStatoConsumabile, String searchIdentificativo,
			ModelloConsumabile searchModello) {
		if (StringUtils.isEmpty(searchIdentificativo) && searchStatoConsumabile == null && searchModello == null)
			return repository.findAll();

		if (StringUtils.isEmpty(searchIdentificativo) && searchStatoConsumabile == null )
			return repository.findByModello(searchModello);

		if (StringUtils.isEmpty(searchIdentificativo) && searchModello == null)
			return repository.findByStato(searchStatoConsumabile);

		if (searchStatoConsumabile == null && searchModello == null)
			return repository.findByIdentificativoContainingIgnoreCase(searchIdentificativo);

		if ( searchModello == null)
			return repository.findByIdentificativoContainingIgnoreCaseAndStato(searchIdentificativo, searchStatoConsumabile);

		if (searchStatoConsumabile == null )
			return repository.findByIdentificativoContainingIgnoreCaseAndModello(searchIdentificativo, searchModello);

		if (StringUtils.isEmpty(searchIdentificativo))
			return repository.findByStatoAndModello(searchStatoConsumabile, searchModello);

		return repository.findByIdentificativoContainingIgnoreCaseAndStatoAndModello(searchIdentificativo, searchStatoConsumabile, searchModello);
	}

	@Override
	public List<ModelloConsumabile> getModelli() {
		return modelliDao.findAll();
	}

	
}
