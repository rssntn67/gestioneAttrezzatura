package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.OperazioneConsumabile;

public interface OperazioneConsumabileDao extends JpaRepository<OperazioneConsumabile, Long> {

    List<OperazioneConsumabile> findByCantiere(Cantiere cantiere);
    List<OperazioneConsumabile> findByOperabile(Consumabile attrezzatura);
    List<OperazioneConsumabile> findByTipoOperazione(TipoOperazione tipo);
    List<OperazioneConsumabile> findByCantiereAndOperabile(Cantiere cantiere, Consumabile attrazzatura);
    List<OperazioneConsumabile> findByCantiereAndTipoOperazione(Cantiere cantiere, TipoOperazione tipo);
    List<OperazioneConsumabile> findByOperabileAndTipoOperazione(Consumabile attrazzatura,TipoOperazione tipo);
	List<OperazioneConsumabile> findByCantiereAndOperabileAndTipoOperazione(Cantiere cantiere, Consumabile operabile,
			TipoOperazione t);
	OperazioneConsumabile findTopByOperabileOrderByIdDesc(Consumabile operabile);
       
}
