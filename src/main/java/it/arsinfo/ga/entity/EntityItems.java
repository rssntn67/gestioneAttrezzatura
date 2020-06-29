package it.arsinfo.ga.entity;

import java.util.List;

import javax.persistence.Transient;

public interface EntityItems<S extends Entity> extends Entity {
    
	@Transient
	boolean addItem(S item);
	@Transient
	boolean removeItem(S item);
	@Transient
	List<S> getItems();

	@Transient
	void setItems(List<S> items);

}
