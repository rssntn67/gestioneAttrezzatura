package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;

public interface OperazioneAttrezzaturaDao extends JpaRepository<OperazioneAttrezzatura, Long> {

    List<OperazioneAttrezzatura> findByCantiere(Cantiere cantiere);
    List<OperazioneAttrezzatura> findByOperabile(Attrezzatura attrezzatura);
    List<OperazioneAttrezzatura> findByTipoOperazione(TipoOperazione tipo);
    List<OperazioneAttrezzatura> findByCantiereAndOperabile(Cantiere cantiere, Attrezzatura attrazzatura);
    List<OperazioneAttrezzatura> findByCantiereAndTipoOperazione(Cantiere cantiere, TipoOperazione tipo);
    List<OperazioneAttrezzatura> findByOperabileAndTipoOperazione(Attrezzatura attrazzatura,TipoOperazione tipo);
    List<OperazioneAttrezzatura> findByCantiereAndOperabileAndTipoOperazione(Cantiere cantiere, Attrezzatura attrazzatura, TipoOperazione tipo);
    OperazioneAttrezzatura findTopByOperabileOrderByIdDesc(Attrezzatura attrezzatura);
       
}
