package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.OperazionePersonale;
import it.arsinfo.ga.model.entity.Personale;

public interface OperazionePersonaleDao extends JpaRepository<OperazionePersonale, Long> {

    List<OperazionePersonale> findByCantiere(Cantiere cantiere);
    List<OperazionePersonale> findByOperabile(Personale attrezzatura);
    List<OperazionePersonale> findByTipoOperazione(TipoOperazione tipo);
    List<OperazionePersonale> findByCantiereAndOperabile(Cantiere cantiere, Personale attrazzatura);
    List<OperazionePersonale> findByCantiereAndTipoOperazione(Cantiere cantiere, TipoOperazione tipo);
    List<OperazionePersonale> findByOperabileAndTipoOperazione(Personale attrazzatura,TipoOperazione tipo);
	List<OperazionePersonale> findByCantiereAndOperabileAndTipoOperazione(Cantiere cantiere, Personale operabile,
			TipoOperazione t);
	OperazionePersonale findTopByOperabileOrderByIdDesc(Personale operabile);
       
}
