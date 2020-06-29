package it.arsinfo.ga.vaadin;

import com.vaadin.icons.VaadinIcons;

import it.arsinfo.ga.entity.Entity;

public abstract class Add<T extends Entity>
        extends CustomButton {

    public Add(String caption) {
        super(caption, VaadinIcons.PLUS);

    }

    public abstract T generate();
    
}
