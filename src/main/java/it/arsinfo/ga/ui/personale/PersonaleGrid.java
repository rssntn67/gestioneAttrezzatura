package it.arsinfo.ga.ui.personale;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.entity.Personale;
import it.arsinfo.ga.vaadin.CustomGrid;

public class PersonaleGrid extends CustomGrid<Personale> {

    public PersonaleGrid(String gridName) {
        super(new Grid<>(Personale.class),gridName);
        setColumns("identificativo","stato","modello.header","numero","utilizzati","disponibili");
        setColumnCaption("modello.header","Modello");
    }

}
