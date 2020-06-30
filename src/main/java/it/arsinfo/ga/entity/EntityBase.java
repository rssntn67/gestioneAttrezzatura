package it.arsinfo.ga.entity;

import javax.persistence.Transient;

public interface EntityBase {
    
	Long getId();
	
	@Transient
	String getHeader();
    
}
