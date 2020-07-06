package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.entity.Cantiere;

public interface CantiereDao extends JpaRepository<Cantiere, Long> {

	Cantiere findByIdentificativo(String nome);
	List<Cantiere> findByIdentificativoContainingIgnoreCase(String nome);
    List<Cantiere> findByStatoCantiere(StatoCantiere stato);
	List<Cantiere> findByIdentificativoContainingIgnoreCaseAndStatoCantiere(String nome,StatoCantiere stato);
    
}
