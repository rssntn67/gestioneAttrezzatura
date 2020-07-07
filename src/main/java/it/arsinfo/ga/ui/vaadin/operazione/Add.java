package it.arsinfo.ga.ui.vaadin.operazione;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;

import it.arsinfo.ga.model.entity.Modello;
import it.arsinfo.ga.model.entity.Operabile;
import it.arsinfo.ga.model.entity.Operazione;
import it.arsinfo.ga.ui.vaadin.UIChangeHandler;

public abstract class Add<K extends Modello,T extends Operabile<K>, S extends Operazione<K, T>> extends UIChangeHandler {

    private final Button button;

    public Add(String caption) {
        button = new Button(caption, VaadinIcons.PLUS);
        button.addClickListener(e -> onChange());
        setComponents(button);
    }
    
    public abstract S generate();
    
}
