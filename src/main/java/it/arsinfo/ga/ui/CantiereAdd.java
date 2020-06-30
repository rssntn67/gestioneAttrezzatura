package it.arsinfo.ga.ui;

import it.arsinfo.ga.entity.Cantiere;
import it.arsinfo.ga.vaadin.Add;

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
