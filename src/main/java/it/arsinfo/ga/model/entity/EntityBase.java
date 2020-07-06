package it.arsinfo.ga.model.entity;

import javax.persistence.Transient;

public interface EntityBase {
    
	Long getId();
	
	@Transient
	String getHeader();
    
}
