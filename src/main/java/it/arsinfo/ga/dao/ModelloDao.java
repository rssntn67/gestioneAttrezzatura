package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.entity.Modello;

@NoRepositoryBean
public interface ModelloDao<M extends Modello> extends JpaRepository<M, Long> {

	List<M> findByNomeAndFornitoreAndAnnoProduzione(String nome, Fornitore fornitore,Anno anno);

	List<M> findByNomeContainingIgnoreCase(String nome);
    List<M> findByAnnoProduzione(Anno anno);
    List<M> findByFornitore(Fornitore fornitore);
    
    List<M> findByNomeContainingIgnoreCaseAndAnnoProduzione(String nome, Anno anno);
    List<M> findByNomeContainingIgnoreCaseAndFornitore(String nome, Fornitore fornitore);
    List<M> findByFornitoreAndAnnoProduzione(Fornitore fornitore, Anno anno);

    List<M> findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzione(String nome, Fornitore fornitore,Anno anno);    
}
