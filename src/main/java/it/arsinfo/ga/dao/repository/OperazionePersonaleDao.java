package it.arsinfo.ga.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.data.TipoOperazione;
import it.arsinfo.ga.entity.Personale;
import it.arsinfo.ga.entity.Cantiere;
import it.arsinfo.ga.entity.OperazionePersonale;

public interface OperazionePersonaleDao extends JpaRepository<OperazionePersonale, Long> {

    List<OperazionePersonale> findByCantiere(Cantiere cantiere);
    List<OperazionePersonale> findByOperabile(Personale attrezzatura);
    List<OperazionePersonale> findByTipoOperazione(TipoOperazione tipo);
    List<OperazionePersonale> findByCantiereAndOperabile(Cantiere cantiere, Personale attrazzatura);
    List<OperazionePersonale> findByCantiereAndTipoOperazione(Cantiere cantiere, TipoOperazione tipo);
    List<OperazionePersonale> findByOperabileAndTipoOperazione(Personale attrazzatura,TipoOperazione tipo);
       
}
