package it.arsinfo.ga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Operabile;
import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.model.entity.Operazione;

@NoRepositoryBean
public interface OperazioneDao<T extends Operabile<?>, S extends Operazione<T>> extends JpaRepository<S, Long> {

    List<S> findByCantiere(Cantiere cantiere);
    List<S> findByOperabile(T operabile);
    List<S> findByOperatore(Operatore operatore);
    List<S> findByTipoOperazione(TipoOperazione tipo);
    
    List<S> findByCantiereAndOperabile(Cantiere cantiere, T operabile);
    List<S> findByCantiereAndTipoOperazione(Cantiere cantiere, TipoOperazione tipo);
    List<S> findByOperabileAndTipoOperazione(T operabile,TipoOperazione tipo);
    List<S> findByOperatoreAndTipoOperazione(Operatore o, TipoOperazione tipo);
	List<S> findByOperabileAndOperatore(T t, Operatore o);
	List<S> findByCantiereAndOperatore(Cantiere c, Operatore o);

    List<S> findByCantiereAndOperabileAndTipoOperazione(Cantiere c, T t, TipoOperazione tipo);
	List<S> findByCantiereAndOperabileAndOperatore(Cantiere c, T t, Operatore o);
	List<S> findByCantiereAndOperatoreAndTipoOperazione(Cantiere c, Operatore o, TipoOperazione tipo);
	List<S> findByOperabileAndOperatoreAndTipoOperazione(T t, Operatore o, TipoOperazione tipo);

    List<S> findByCantiereAndOperabileAndOperatoreAndTipoOperazione(Cantiere c, T operabile, Operatore o,TipoOperazione tipo);

	S findTopByOperabileOrderByIdDesc(T operabile);
	
}
