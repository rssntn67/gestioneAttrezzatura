
package it.arsinfo.ga.ui.vaadin.entity;

import java.util.List;

import it.arsinfo.ga.model.entity.EntityBase;
import it.arsinfo.ga.service.EntityBaseService;
import it.arsinfo.ga.ui.vaadin.UIChangeHandler;

public abstract class Search<T extends EntityBase>
        extends UIChangeHandler {

    private final EntityBaseService<T> dao;
        
    public Search(EntityBaseService<T> dao) {
        this.dao=dao;

    }

    public abstract List<T> find();
        
    public List<T> findAll() {
        return dao.findAll();
    }

}
