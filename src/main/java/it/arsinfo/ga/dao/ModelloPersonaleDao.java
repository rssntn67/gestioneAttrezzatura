package it.arsinfo.ga.dao;

import java.util.List;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.TipoPersonale;
import it.arsinfo.ga.model.entity.ModelloPersonale;

public interface ModelloPersonaleDao extends ModelloDao<ModelloPersonale> {

    List<ModelloPersonale> findByTipo(TipoPersonale tipo);
    
    List<ModelloPersonale> findByNomeContainingIgnoreCaseAndTipo(String nome, TipoPersonale tipo);
    List<ModelloPersonale> findByFornitoreAndTipo(Fornitore fornitore, TipoPersonale tipo);
    List<ModelloPersonale> findByAnnoProduzioneAndTipo(Anno anno, TipoPersonale tipo);

    List<ModelloPersonale> findByNomeContainingIgnoreCaseAndFornitoreAndTipo(String nome, Fornitore fornitore, TipoPersonale tipo);
    List<ModelloPersonale> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipo(String nome, Anno anno, TipoPersonale tipo);
    List<ModelloPersonale> findByFornitoreAndAnnoProduzioneAndTipo(Fornitore fornitore,Anno anno, TipoPersonale tipo);

    List<ModelloPersonale> findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzioneAndTipo(String nome, Fornitore fornitore,Anno anno, TipoPersonale tipo);

}
