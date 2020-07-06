package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.ModelloConsumabile;

public interface ConsumabileDao extends JpaRepository<Consumabile, Long> {

	Consumabile findByIdentificativo(String nome);
	
	List<Consumabile> findByIdentificativoContainingIgnoreCase(String nome);
    List<Consumabile> findByStato(StatoOperabile stato);
    List<Consumabile> findByModello(ModelloConsumabile modello);

    List<Consumabile> findByIdentificativoContainingIgnoreCaseAndStato(String nome,StatoOperabile stato);
	List<Consumabile> findByIdentificativoContainingIgnoreCaseAndModello(String nome,ModelloConsumabile modello);
    List<Consumabile> findByStatoAndModello(StatoOperabile stato,ModelloConsumabile modello);
	List<Consumabile> findByIdentificativoContainingIgnoreCaseAndStatoAndModello(String nome,StatoOperabile stato,ModelloConsumabile modello);
    
}
