package it.arsinfo.ga.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.entity.Cantiere;

@Service
public interface CantiereService extends ServiceDao<Cantiere>{

	List<Cantiere> searchBy(String searchIdentificativo, StatoCantiere statoCantiere);

}
