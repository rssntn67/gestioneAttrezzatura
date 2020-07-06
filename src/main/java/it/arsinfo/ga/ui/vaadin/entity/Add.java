package it.arsinfo.ga.ui.vaadin.entity;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;

import it.arsinfo.ga.model.entity.EntityBase;
import it.arsinfo.ga.ui.vaadin.UIChangeHandler;

public abstract class Add<T extends EntityBase>
        extends UIChangeHandler {

    private final Button button;

    public Add(String caption) {
        button = new Button(caption, VaadinIcons.PLUS);
        button.addClickListener(e -> onChange());
        setComponents(button);
    }

    public abstract T generate();
    
    public void enable() {
    	button.setEnabled(true);
    }
    
    public void disable() {
    	button.setEnabled(false);
    }

}
