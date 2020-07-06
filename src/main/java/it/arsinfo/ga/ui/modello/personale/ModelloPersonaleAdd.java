package it.arsinfo.ga.ui.modello.personale;

import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.ui.vaadin.entity.Add;

public class ModelloPersonaleAdd extends Add<ModelloPersonale> {

    public ModelloPersonaleAdd(String caption) {
        super(caption);
    }
    
    @Override
    public ModelloPersonale generate() {
    	ModelloPersonale modelloPersonale = new ModelloPersonale();
    	modelloPersonale.setNome("");
        return modelloPersonale;
    }

}
