package it.arsinfo.ga.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.data.TipoOperazione;
import it.arsinfo.ga.entity.Attrezzatura;
import it.arsinfo.ga.entity.Cantiere;
import it.arsinfo.ga.entity.Operazione;

public interface OperazioneDao extends JpaRepository<Operazione, Long> {

    List<Operazione> findByTipoOperazione(TipoOperazione stato);
    List<Operazione> findByCantiere(Cantiere cantiere);
    List<Operazione> findByAttrezzatura(Attrezzatura attrezzatura);
       
}
