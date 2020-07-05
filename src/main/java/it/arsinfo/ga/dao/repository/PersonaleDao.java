package it.arsinfo.ga.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.data.StatoOperabile;
import it.arsinfo.ga.entity.Personale;
import it.arsinfo.ga.entity.ModelloPersonale;

public interface PersonaleDao extends JpaRepository<Personale, Long> {

	Personale findByIdentificativo(String nome);
	
	List<Personale> findByIdentificativoContainingIgnoreCase(String nome);
    List<Personale> findByStato(StatoOperabile stato);
    List<Personale> findByModello(ModelloPersonale modello);

    List<Personale> findByIdentificativoContainingIgnoreCaseAndStato(String nome,StatoOperabile stato);
	List<Personale> findByIdentificativoContainingIgnoreCaseAndModello(String nome,ModelloPersonale modello);
    List<Personale> findByStatoAndModello(StatoOperabile stato,ModelloPersonale modello);
	List<Personale> findByIdentificativoContainingIgnoreCaseAndStatoAndModello(String nome,StatoOperabile stato,ModelloPersonale modello);
    
}
