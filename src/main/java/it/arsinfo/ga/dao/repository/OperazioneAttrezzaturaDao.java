package it.arsinfo.ga.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.data.TipoOperazione;
import it.arsinfo.ga.entity.Attrezzatura;
import it.arsinfo.ga.entity.Cantiere;
import it.arsinfo.ga.entity.OperazioneAttrezzatura;

public interface OperazioneAttrezzaturaDao extends JpaRepository<OperazioneAttrezzatura, Long> {

    List<OperazioneAttrezzatura> findByCantiere(Cantiere cantiere);
    List<OperazioneAttrezzatura> findByOperabile(Attrezzatura attrezzatura);
    List<OperazioneAttrezzatura> findByTipoOperazione(TipoOperazione tipo);
    List<OperazioneAttrezzatura> findByCantiereAndOperabile(Cantiere cantiere, Attrezzatura attrazzatura);
    List<OperazioneAttrezzatura> findByCantiereAndTipoOperazione(Cantiere cantiere, TipoOperazione tipo);
    List<OperazioneAttrezzatura> findByOperabileAndTipoOperazione(Attrezzatura attrazzatura,TipoOperazione tipo);
       
}
