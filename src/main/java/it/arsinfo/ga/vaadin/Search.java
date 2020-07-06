
package it.arsinfo.ga.vaadin;

import java.util.List;

import it.arsinfo.ga.model.entity.EntityBase;
import it.arsinfo.ga.service.ServiceDao;

public abstract class Search<T extends EntityBase>
        extends UIChangeHandler {

    private final ServiceDao<T> dao;
        
    public Search(ServiceDao<T> dao) {
        this.dao=dao;

    }

    public abstract List<T> find();
        
    public List<T> findAll() {
        return dao.findAll();
    }

}
