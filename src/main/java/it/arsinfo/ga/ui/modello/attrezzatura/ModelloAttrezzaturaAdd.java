package it.arsinfo.ga.ui.modello.attrezzatura;

import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.ui.vaadin.entity.Add;

public class ModelloAttrezzaturaAdd extends Add<ModelloAttrezzatura> {

    public ModelloAttrezzaturaAdd(String caption) {
        super(caption);
    }
    
    @Override
    public ModelloAttrezzatura generate() {
    	ModelloAttrezzatura modelloAttrezzatura = new ModelloAttrezzatura();
    	modelloAttrezzatura.setNome("");
        return modelloAttrezzatura;
    }

}
