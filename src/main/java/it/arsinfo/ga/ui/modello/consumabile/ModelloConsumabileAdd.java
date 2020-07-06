package it.arsinfo.ga.ui.modello.consumabile;

import it.arsinfo.ga.model.entity.ModelloConsumabile;
import it.arsinfo.ga.ui.vaadin.entity.Add;

public class ModelloConsumabileAdd extends Add<ModelloConsumabile> {

    public ModelloConsumabileAdd(String caption) {
        super(caption);
    }
    
    @Override
    public ModelloConsumabile generate() {
    	ModelloConsumabile modelloConsumabile = new ModelloConsumabile();
    	modelloConsumabile.setNome("");
        return modelloConsumabile;
    }

}
