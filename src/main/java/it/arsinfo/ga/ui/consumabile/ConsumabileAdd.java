package it.arsinfo.ga.ui.consumabile;

import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.ui.vaadin.Add;

public class ConsumabileAdd extends Add<Consumabile> {

    public ConsumabileAdd(String caption) {
        super(caption);
    }
    
    @Override
    public Consumabile generate() {
    	Consumabile attrezzatura = new Consumabile();
    	attrezzatura.setIdentificativo("");
        return attrezzatura;
    }

}
