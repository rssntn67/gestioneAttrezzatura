package it.arsinfo.ga.entity;

import javax.persistence.Transient;

public interface Entity {
    
	Long getId();
	
	@Transient
	String getHeader();
    
}
