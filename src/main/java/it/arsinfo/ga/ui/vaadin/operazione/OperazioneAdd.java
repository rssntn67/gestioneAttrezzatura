package it.arsinfo.ga.ui.vaadin.operazione;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;

import it.arsinfo.ga.model.entity.Operazione;
import it.arsinfo.ga.ui.vaadin.UIChangeHandler;

public abstract class OperazioneAdd<S extends Operazione<?>> extends UIChangeHandler {

    private final Button button;

    public OperazioneAdd(String caption) {
        button = new Button(caption, VaadinIcons.PLUS);
        button.addClickListener(e -> onChange());
        setComponents(button);
    }
    
    public abstract S generate();
    
}
