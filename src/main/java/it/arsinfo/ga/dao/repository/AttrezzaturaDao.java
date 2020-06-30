package it.arsinfo.ga.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.data.StatoAttrezzatura;
import it.arsinfo.ga.entity.Attrezzatura;
import it.arsinfo.ga.entity.ModelloAttrezzatura;

public interface AttrezzaturaDao extends JpaRepository<Attrezzatura, Long> {

	Attrezzatura findByIdentificativo(String nome);
	List<Attrezzatura> findByIdentificativoContainingIgnoreCase(String nome);
    List<Attrezzatura> findByStatoAttrezzatura(StatoAttrezzatura stato);
    List<Attrezzatura> findByModelloAttrezzatura(ModelloAttrezzatura modello);
	List<Attrezzatura> findByIdentificativoContainingIgnoreCaseAndStatoAttrezzatura(String nome,StatoAttrezzatura stato);
	List<Attrezzatura> findByIdentificativoContainingIgnoreCaseAndModelloAttrezzatura(String nome,ModelloAttrezzatura modello);
    List<Attrezzatura> findByStatoAttrezzaturaAndModelloAttrezzatura(StatoAttrezzatura stato,ModelloAttrezzatura modello);
	List<Attrezzatura> findByIdentificativoContainingIgnoreCaseAndStatoAttrezzaturaAndModelloAttrezzatura(String nome,StatoAttrezzatura stato,ModelloAttrezzatura modello);
    
}
