package it.arsinfo.ga.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.data.TipoOperazioneAttrezzatura;
import it.arsinfo.ga.entity.Attrezzatura;
import it.arsinfo.ga.entity.Cantiere;
import it.arsinfo.ga.entity.OperazioneAttrezzatura;

public interface OperazioneDao extends JpaRepository<OperazioneAttrezzatura, Long> {

    List<OperazioneAttrezzatura> findByTipoOperazione(TipoOperazioneAttrezzatura stato);
    List<OperazioneAttrezzatura> findByCantiere(Cantiere cantiere);
    List<OperazioneAttrezzatura> findByAttrezzatura(Attrezzatura attrezzatura);
       
}
