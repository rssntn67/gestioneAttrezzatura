package it.arsinfo.ga.ui.modello.attrezzatura;

import it.arsinfo.ga.entity.ModelloAttrezzatura;
import it.arsinfo.ga.vaadin.Add;

public class ModelloAttrezzaturaAdd extends Add<ModelloAttrezzatura> {

    public ModelloAttrezzaturaAdd(String caption) {
        super(caption);
    }
    
    @Override
    public ModelloAttrezzatura generate() {
    	ModelloAttrezzatura modelloAttrezzatura = new ModelloAttrezzatura();
    	modelloAttrezzatura.setNome("InserisciNome");
        return modelloAttrezzatura;
    }

}
