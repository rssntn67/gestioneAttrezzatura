package it.arsinfo.ga.dao;

import java.util.List;

import it.arsinfo.ga.entity.EntityBase;
import it.arsinfo.ga.entity.EntityItems;

public interface ServiceItemDao<T extends EntityItems<I>,I extends EntityBase> extends ServiceDao<T>{
	List<I>	getItems(T t);
	T deleteItem(T t,I item) throws Exception;
	T saveItem(T t,I item) throws Exception;
	List<I> findAllItems();
}
