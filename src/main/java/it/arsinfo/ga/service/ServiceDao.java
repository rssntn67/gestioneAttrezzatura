package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.entity.EntityBase;


public interface ServiceDao<S extends EntityBase> {
	
	S save(S entity) throws Exception;
	void delete(S entity) throws Exception ;
	S findById(Long id);
	List<S> findAll();
}
