package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.MarcaAttrezzatura;
import it.arsinfo.ga.model.data.TipoAttrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;

public interface ModelloAttrezzaturaDao extends JpaRepository<ModelloAttrezzatura, Long> {

	ModelloAttrezzatura findByNomeAndFornitoreAndAnnoProduzione(String nome, Fornitore fornitore,Anno anno);

	List<ModelloAttrezzatura> findByNomeContainingIgnoreCase(String nome);
    List<ModelloAttrezzatura> findByAnnoProduzione(Anno anno);
    List<ModelloAttrezzatura> findByFornitore(Fornitore fornitore);
    List<ModelloAttrezzatura> findByMarca(MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByTipo(TipoAttrezzatura tipo);
    
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndAnnoProduzione(String nome, Anno anno);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndFornitore(String nome, Fornitore fornitore);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndTipo(String nome, TipoAttrezzatura tipo);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndMarca(String nome, MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByFornitoreAndAnnoProduzione(Fornitore fornitore, Anno anno);
    List<ModelloAttrezzatura> findByFornitoreAndTipo(Fornitore fornitore, TipoAttrezzatura tipo);
    List<ModelloAttrezzatura> findByFornitoreAndMarca(Fornitore fornitore, MarcaAttrezzatura tipo);
    List<ModelloAttrezzatura> findByAnnoProduzioneAndTipo(Anno anno, TipoAttrezzatura tipo);
    List<ModelloAttrezzatura> findByAnnoProduzioneAndMarca(Anno anno, MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByMarcaAndTipo(MarcaAttrezzatura marca, TipoAttrezzatura tipo);

    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzione(String nome, Fornitore fornitore,Anno anno);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndFornitoreAndTipo(String nome, Fornitore fornitore, TipoAttrezzatura tipo);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndFornitoreAndMarca(String nome, Fornitore fornitore, MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipo(String nome, Anno anno, TipoAttrezzatura tipo);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndMarca(String nome, Anno anno, MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndTipoAndMarca(String nome, TipoAttrezzatura tipo, MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByFornitoreAndAnnoProduzioneAndTipo(Fornitore fornitore,Anno anno, TipoAttrezzatura tipo);
    List<ModelloAttrezzatura> findByFornitoreAndAnnoProduzioneAndMarca(Fornitore fornitore,Anno anno, MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByFornitoreAndTipoAndMarca(Fornitore fornitore, TipoAttrezzatura tipo, MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByAnnoProduzioneAndTipoAndMarca(Anno anno, TipoAttrezzatura tipo, MarcaAttrezzatura marca);

    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzioneAndTipo(String nome, Fornitore fornitore,Anno anno, TipoAttrezzatura tipo);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzioneAndMarca(String nome, Fornitore fornitore,Anno anno,MarcaAttrezzatura tipo);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndFornitoreAndTipoAndMarca(String nome, Fornitore fornitore,TipoAttrezzatura tipo,MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipoAndMarca(String nome, Anno anno, TipoAttrezzatura tipo,MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByFornitoreAndAnnoProduzioneAndTipoAndMarca(Fornitore nome, Anno anno, TipoAttrezzatura tipo,MarcaAttrezzatura marca);

    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzioneAndTipoAndMarca(String nome, Fornitore fornitore,Anno anno, TipoAttrezzatura tipo,MarcaAttrezzatura marca);

}
