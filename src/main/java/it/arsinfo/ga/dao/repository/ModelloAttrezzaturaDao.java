package it.arsinfo.ga.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.MarcaModello;
import it.arsinfo.ga.data.TipoModello;
import it.arsinfo.ga.entity.ModelloAttrezzatura;

public interface ModelloAttrezzaturaDao extends JpaRepository<ModelloAttrezzatura, Long> {

	ModelloAttrezzatura findByNome(String nome);
	List<ModelloAttrezzatura> findByNomeContainingIgnoreCase(String nome);
    List<ModelloAttrezzatura> findByAnnoProduzione(Anno anno);
    List<ModelloAttrezzatura> findByMarcaModello(MarcaModello marca);
    List<ModelloAttrezzatura> findByTipoModello(TipoModello tipo);
    
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndAnnoProduzione(String nome, Anno anno);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndTipoModello(String nome, TipoModello tipo);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndMarcaModello(String nome, MarcaModello marca);
    List<ModelloAttrezzatura> findByAnnoProduzioneAndTipoModello(Anno anno, TipoModello tipo);
    List<ModelloAttrezzatura> findByAnnoProduzioneAndMarcaModello(Anno anno, MarcaModello marca);
    List<ModelloAttrezzatura> findByMarcaModelloAndTipoModello(MarcaModello marca, TipoModello tipo);

    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipoModello(String nome, Anno anno, TipoModello tipo);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndMarcaModello(String nome, Anno anno, MarcaModello marca);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndTipoModelloAndMarcaModello(String nome, TipoModello tipo, MarcaModello marca);
    List<ModelloAttrezzatura> findByAnnoProduzioneAndTipoModelloAndMarcaModello(Anno anno, TipoModello tipo, MarcaModello marca);

    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipoModelloAndMarcaModello(String nome, Anno anno, TipoModello tipo,MarcaModello marca);
    
}
