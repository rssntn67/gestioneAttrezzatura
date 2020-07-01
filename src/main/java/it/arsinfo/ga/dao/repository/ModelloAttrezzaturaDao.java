package it.arsinfo.ga.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.MarcaAttrezzatura;
import it.arsinfo.ga.data.TipoAttrezzatura;
import it.arsinfo.ga.entity.ModelloAttrezzatura;

public interface ModelloAttrezzaturaDao extends JpaRepository<ModelloAttrezzatura, Long> {

	ModelloAttrezzatura findByNome(String nome);
	List<ModelloAttrezzatura> findByNomeContainingIgnoreCase(String nome);
    List<ModelloAttrezzatura> findByAnnoProduzione(Anno anno);
    List<ModelloAttrezzatura> findByMarcaModello(MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByTipoModello(TipoAttrezzatura tipo);
    
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndAnnoProduzione(String nome, Anno anno);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndTipoModello(String nome, TipoAttrezzatura tipo);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndMarcaModello(String nome, MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByAnnoProduzioneAndTipoModello(Anno anno, TipoAttrezzatura tipo);
    List<ModelloAttrezzatura> findByAnnoProduzioneAndMarcaModello(Anno anno, MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByMarcaModelloAndTipoModello(MarcaAttrezzatura marca, TipoAttrezzatura tipo);

    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipoModello(String nome, Anno anno, TipoAttrezzatura tipo);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndMarcaModello(String nome, Anno anno, MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndTipoModelloAndMarcaModello(String nome, TipoAttrezzatura tipo, MarcaAttrezzatura marca);
    List<ModelloAttrezzatura> findByAnnoProduzioneAndTipoModelloAndMarcaModello(Anno anno, TipoAttrezzatura tipo, MarcaAttrezzatura marca);

    List<ModelloAttrezzatura> findByNomeContainingIgnoreCaseAndAnnoProduzioneAndTipoModelloAndMarcaModello(String nome, Anno anno, TipoAttrezzatura tipo,MarcaAttrezzatura marca);
    
}
