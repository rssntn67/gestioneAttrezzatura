package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.model.data.StatoOperatore;
import it.arsinfo.ga.model.entity.Operatore;

public interface OperatoreDao extends JpaRepository<Operatore, Long> {

	Operatore findByIdentificativo(String nome);
    Operatore findByApikey(String apiKey);

    List<Operatore> findByStato(StatoOperatore stato);
    List<Operatore> findByEmail(String email);
    List<Operatore> findByTelefono(String telefono);
	List<Operatore> findByIdentificativoContainingIgnoreCase(String nome);
	List<Operatore> findByIdentificativoContainingIgnoreCaseAndStato(String nome,StatoOperatore stato);
    
}
