package it.arsinfo.ga.ui.personale;

import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.vaadin.Add;

public class PersonaleAdd extends Add<Personale> {

    public PersonaleAdd(String caption) {
        super(caption);
    }
    
    @Override
    public Personale generate() {
    	Personale attrezzatura = new Personale();
    	attrezzatura.setIdentificativo("");
        return attrezzatura;
    }

}
