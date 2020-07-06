package it.arsinfo.ga.ui.cantiere;

import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.ui.vaadin.Add;

public class CantiereAdd extends Add<Cantiere> {

    public CantiereAdd(String caption) {
        super(caption);
    }
    
    @Override
    public Cantiere generate() {
    	Cantiere cantiere = new Cantiere();
    	cantiere.setIdentificativo("InserisciIdentificativoUnico");
        return cantiere;
    }

}
