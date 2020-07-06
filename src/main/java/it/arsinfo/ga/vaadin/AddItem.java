package it.arsinfo.ga.vaadin;

import it.arsinfo.ga.model.entity.EntityBase;
import it.arsinfo.ga.model.entity.EntityItems;

public abstract class AddItem<I extends EntityBase, T extends EntityItems<I>>
        extends Add<I> {

    public AddItem(String caption) {
        super(caption);

    }
    
    public abstract void set(T t);
    
    
}
