package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;

public interface AttrezzaturaDao extends JpaRepository<Attrezzatura, Long> {

	Attrezzatura findByIdentificativo(String nome);
	
	List<Attrezzatura> findByIdentificativoContainingIgnoreCase(String nome);
    List<Attrezzatura> findByStato(StatoOperabile stato);
    List<Attrezzatura> findByModello(ModelloAttrezzatura modello);

    List<Attrezzatura> findByIdentificativoContainingIgnoreCaseAndStato(String nome,StatoOperabile stato);
	List<Attrezzatura> findByIdentificativoContainingIgnoreCaseAndModello(String nome,ModelloAttrezzatura modello);
    List<Attrezzatura> findByStatoAndModello(StatoOperabile stato,ModelloAttrezzatura modello);
	List<Attrezzatura> findByIdentificativoContainingIgnoreCaseAndStatoAndModello(String nome,StatoOperabile stato,ModelloAttrezzatura modello);

	List<Attrezzatura> findByStatoNot(StatoOperabile dismesso);

	List<Attrezzatura> findByStatoNotOrStatoNot(StatoOperabile dismesso, StatoOperabile inriparazione);
    
}
