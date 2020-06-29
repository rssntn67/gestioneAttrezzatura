package it.arsinfo.ga.vaadin;

import it.arsinfo.ga.entity.Entity;
import it.arsinfo.ga.entity.EntityItems;

public abstract class AddItem<I extends Entity, T extends EntityItems<I>>
        extends Add<I> {

    public AddItem(String caption) {
        super(caption);

    }
    
    public abstract void set(T t);
    
    
}
