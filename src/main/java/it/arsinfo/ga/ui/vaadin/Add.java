package it.arsinfo.ga.ui.vaadin;

import com.vaadin.icons.VaadinIcons;

import it.arsinfo.ga.model.entity.EntityBase;

public abstract class Add<T extends EntityBase>
        extends CustomButton {

    public Add(String caption) {
        super(caption, VaadinIcons.PLUS);

    }

    public abstract T generate();
    
}
