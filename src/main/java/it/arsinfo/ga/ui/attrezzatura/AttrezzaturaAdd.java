package it.arsinfo.ga.ui.attrezzatura;

import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.ui.vaadin.entity.Add;

public class AttrezzaturaAdd extends Add<Attrezzatura> {

    public AttrezzaturaAdd(String caption) {
        super(caption);
    }
    
    @Override
    public Attrezzatura generate() {
    	Attrezzatura attrezzatura = new Attrezzatura();
    	attrezzatura.setIdentificativo("");
        return attrezzatura;
    }

}
