package it.arsinfo.ga.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.arsinfo.ga.dao.AttrezzaturaServiceDao;
import it.arsinfo.ga.dao.repository.AttrezzaturaDao;
import it.arsinfo.ga.dao.repository.ModelloAttrezzaturaDao;
import it.arsinfo.ga.data.StatoAttrezzatura;
import it.arsinfo.ga.entity.Attrezzatura;
import it.arsinfo.ga.entity.ModelloAttrezzatura;

@Service
public class AttrezzaturaServiceDaoImpl implements AttrezzaturaServiceDao {

    @Autowired
    private AttrezzaturaDao repository;

    @Autowired
    private ModelloAttrezzaturaDao modelliDao;

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
	public List<Attrezzatura> searchBy(StatoAttrezzatura searchStatoAttrezzatura, String searchIdentificativo,
			ModelloAttrezzatura searchModello) {
		if (StringUtils.isEmpty(searchIdentificativo) && searchStatoAttrezzatura == null && searchModello == null)
			return repository.findAll();

		if (StringUtils.isEmpty(searchIdentificativo) && searchStatoAttrezzatura == null )
			return repository.findByModelloAttrezzatura(searchModello);

		if (StringUtils.isEmpty(searchIdentificativo) && searchModello == null)
			return repository.findByStatoAttrezzatura(searchStatoAttrezzatura);

		if (searchStatoAttrezzatura == null && searchModello == null)
			return repository.findByIdentificativoContainingIgnoreCase(searchIdentificativo);

		if ( searchModello == null)
			return repository.findByIdentificativoContainingIgnoreCaseAndStatoAttrezzatura(searchIdentificativo, searchStatoAttrezzatura);

		if (searchStatoAttrezzatura == null )
			return repository.findByIdentificativoContainingIgnoreCaseAndModelloAttrezzatura(searchIdentificativo, searchModello);

		if (StringUtils.isEmpty(searchIdentificativo))
			return repository.findByStatoAttrezzaturaAndModelloAttrezzatura(searchStatoAttrezzatura, searchModello);

		return repository.findByIdentificativoContainingIgnoreCaseAndStatoAttrezzaturaAndModelloAttrezzatura(searchIdentificativo, searchStatoAttrezzatura, searchModello);
	}

	@Override
	public List<ModelloAttrezzatura> getModelli() {
		return modelliDao.findAll();
	}

	
}
