package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import it.arsinfo.ga.data.StatoCantiere;
import it.arsinfo.ga.entity.Cantiere;

@Service
public interface CantiereServiceDao extends ServiceDao<Cantiere>{

	List<Cantiere> searchBy(String searchIdentificativo, StatoCantiere statoCantiere);

}
