package it.arsinfo.ga.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.Fornitore;
import it.arsinfo.ga.data.TipoPersonale;
import it.arsinfo.ga.entity.ModelloPersonale;

public interface ModelloPersonaleDao extends JpaRepository<ModelloPersonale, Long> {

	ModelloPersonale findByNomeAndFornitoreAndAnnoProduzione(String nome, Fornitore fornitore,Anno anno);

	List<ModelloPersonale> findByNomeContainingIgnoreCase(String nome);
    List<ModelloPersonale> findByAnnoProduzione(Anno anno);
    List<ModelloPersonale> findByFornitore(Fornitore fornitore);
    List<ModelloPersonale> findByTipo(TipoPersonale tipo);
    
    List<ModelloPersonale> findByNomeContainingIgnoreCaseAndAnnoProduzione(String nome, Anno anno);
    List<ModelloPersonale> findByNomeContainingIgnoreCaseAndFornitore(String nome, Fornitore fornitore);
    List<ModelloPersonale> findByNomeContainingIgnoreCaseAndTipo(String nome, TipoPersonale tipo);
    List<ModelloPersonale> findByFornitoreAndAnnoProduzione(Fornitore fornitore, Anno anno);
    List<ModelloPersonale> findByFornitoreAndTipo(Fornitore fornitore, TipoPersonale tipo);
    List<ModelloPersonale> findByAnnoProduzioneAndTipo(Anno anno, TipoPersonale tipo);

    List<ModelloPersonale> findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzione(String nome, Fornitore fornitore,Anno anno);
    List<ModelloPersonale> findByNomeContainingIgnoreCaseAndFornitoreAndTipo(String nome, Fornitore fornitore, TipoPersonale tipo);
    List<ModelloPersonale> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipo(String nome, Anno anno, TipoPersonale tipo);
    List<ModelloPersonale> findByFornitoreAndAnnoProduzioneAndTipo(Anno anno, Fornitore fornitore,TipoPersonale tipo);

    List<ModelloPersonale> findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzioneAndTipo(String nome, Fornitore fornitore,Anno anno, TipoPersonale tipo);

}
