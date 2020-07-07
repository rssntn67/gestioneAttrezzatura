package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.TipoCantiere;
import it.arsinfo.ga.model.entity.Cantiere;

public interface CantiereDao extends JpaRepository<Cantiere, Long> {

	Cantiere findByIdentificativo(String nome);

	List<Cantiere> findByIdentificativoContainingIgnoreCase(String nome);
    List<Cantiere> findByStato(StatoCantiere stato);
    List<Cantiere> findByTipo(TipoCantiere tipo);
	List<Cantiere> findByIdentificativoContainingIgnoreCaseAndStato(String nome,StatoCantiere stato);
	List<Cantiere> findByIdentificativoContainingIgnoreCaseAndTipo(String nome,TipoCantiere tipo);
	List<Cantiere> findByStatoAndTipo(StatoCantiere stato,TipoCantiere tipo);
	List<Cantiere> findByIdentificativoContainingIgnoreCaseAndStatoAndTipo(String nome,StatoCantiere stato,TipoCantiere tipo);
    
}
