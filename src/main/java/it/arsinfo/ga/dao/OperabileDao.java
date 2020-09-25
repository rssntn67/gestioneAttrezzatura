package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Modello;
import it.arsinfo.ga.model.entity.Operabile;

@NoRepositoryBean
public interface OperabileDao<M extends Modello, T extends Operabile<M>> extends JpaRepository<T, Long> {

	T findByIdentificativo(String nome);
	
	List<T> findByIdentificativoContainingIgnoreCase(String nome);
    List<T> findByStato(StatoOperabile stato);
    List<T> findByModello(M modello);

    List<T> findByIdentificativoContainingIgnoreCaseAndStato(String nome,StatoOperabile stato);
	List<T> findByIdentificativoContainingIgnoreCaseAndModello(String nome,M modello);
    List<T> findByStatoAndModello(StatoOperabile stato,M modello);
	List<T> findByIdentificativoContainingIgnoreCaseAndStatoAndModello(String nome,StatoOperabile stato,M modello);

	List<T> findByStatoNot(StatoOperabile dismesso);

	List<T> findByStatoNotOrStatoNot(StatoOperabile dismesso, StatoOperabile inriparazione);
    
}
