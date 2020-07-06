package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.MarcaConsumabile;
import it.arsinfo.ga.model.data.TipoConsumabile;
import it.arsinfo.ga.model.entity.ModelloConsumabile;

public interface ModelloConsumabileDao extends JpaRepository<ModelloConsumabile, Long> {

	ModelloConsumabile findByNomeAndFornitoreAndAnnoProduzione(String nome, Fornitore fornitore,Anno anno);

	List<ModelloConsumabile> findByNomeContainingIgnoreCase(String nome);
    List<ModelloConsumabile> findByAnnoProduzione(Anno anno);
    List<ModelloConsumabile> findByFornitore(Fornitore fornitore);
    List<ModelloConsumabile> findByMarca(MarcaConsumabile marca);
    List<ModelloConsumabile> findByTipo(TipoConsumabile tipo);
    
    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndAnnoProduzione(String nome, Anno anno);
    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndFornitore(String nome, Fornitore fornitore);
    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndTipo(String nome, TipoConsumabile tipo);
    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndMarca(String nome, MarcaConsumabile marca);
    List<ModelloConsumabile> findByFornitoreAndAnnoProduzione(Fornitore fornitore, Anno anno);
    List<ModelloConsumabile> findByFornitoreAndTipo(Fornitore fornitore, TipoConsumabile tipo);
    List<ModelloConsumabile> findByFornitoreAndMarca(Fornitore fornitore, MarcaConsumabile marca);
    List<ModelloConsumabile> findByAnnoProduzioneAndTipo(Anno anno, TipoConsumabile tipo);
    List<ModelloConsumabile> findByAnnoProduzioneAndMarca(Anno anno, MarcaConsumabile marca);
    List<ModelloConsumabile> findByMarcaAndTipo(MarcaConsumabile marca, TipoConsumabile tipo);

    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzione(String nome, Fornitore fornitore,Anno anno);
    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndFornitoreAndTipo(String nome, Fornitore fornitore, TipoConsumabile tipo);
    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndFornitoreAndMarca(String nome, Fornitore fornitore, MarcaConsumabile marca);
    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipo(String nome, Anno anno, TipoConsumabile tipo);
    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndMarca(String nome, Anno anno, MarcaConsumabile marca);
    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndTipoAndMarca(String nome, TipoConsumabile tipo, MarcaConsumabile marca);
    List<ModelloConsumabile> findByFornitoreAndAnnoProduzioneAndTipo(Fornitore fornitore,Anno anno, TipoConsumabile tipo);
    List<ModelloConsumabile> findByFornitoreAndAnnoProduzioneAndMarca(Fornitore fornitore,Anno anno, MarcaConsumabile marca);
    List<ModelloConsumabile> findByFornitoreAndTipoAndMarca(Fornitore fornitore, TipoConsumabile tipo, MarcaConsumabile marca);
    List<ModelloConsumabile> findByAnnoProduzioneAndTipoAndMarca(Anno anno, TipoConsumabile tipo, MarcaConsumabile marca);

    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzioneAndTipo(String nome, Fornitore fornitore,Anno anno, TipoConsumabile tipo);
    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzioneAndMarca(String nome, Fornitore fornitore,Anno anno,MarcaConsumabile tipo);
    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndFornitoreAndTipoAndMarca(String nome, Fornitore fornitore,TipoConsumabile tipo,MarcaConsumabile marca);
    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipoAndMarca(String nome, Anno anno, TipoConsumabile tipo,MarcaConsumabile marca);
    List<ModelloConsumabile> findByFornitoreAndAnnoProduzioneAndTipoAndMarca(Fornitore nome, Anno anno, TipoConsumabile tipo,MarcaConsumabile marca);

    List<ModelloConsumabile> findByNomeContainingIgnoreCaseAndFornitoreAndAnnoProduzioneAndTipoAndMarca(String nome, Fornitore fornitore,Anno anno, TipoConsumabile tipo,MarcaConsumabile marca);

}
