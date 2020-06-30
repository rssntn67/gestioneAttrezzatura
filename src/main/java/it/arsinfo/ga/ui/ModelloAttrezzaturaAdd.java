package it.arsinfo.ga.ui;

import it.arsinfo.ga.data.Anno;
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
    	modelloAttrezzatura.setAnnoProduzione(Anno.ANNOND);
        return modelloAttrezzatura;
    }

}
