
package it.arsinfo.ga.ui.vaadin;

import java.util.List;
import java.util.Set;

import it.arsinfo.ga.model.entity.EntityBase;

public abstract class CheckBoxArrayMapper<T extends EntityBase, K extends EntityBase>
        extends CheckBoxArray<T> {


    public CheckBoxArrayMapper(List<T> provided) {
        super(provided);
    }
    
    public void edit(List<K> items, boolean persisted) {
        getLayout().removeAllComponents();
        getSelected().clear();
        Set<Long> matchers = match(items);
        getProvided().stream().forEach(t -> {
            getLayout().addComponent(generaBox(t,matchers.contains(t.getId()),persisted));                
        });
        getLayout().setVisible(true);
    }

    public abstract Set<Long> match(List<K> items);
    
}