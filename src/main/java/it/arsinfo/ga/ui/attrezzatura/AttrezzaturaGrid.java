package it.arsinfo.ga.ui.attrezzatura;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.entity.Attrezzatura;
import it.arsinfo.ga.vaadin.CustomGrid;

public class AttrezzaturaGrid extends CustomGrid<Attrezzatura> {

    public AttrezzaturaGrid(String gridName) {
        super(new Grid<>(Attrezzatura.class),gridName);
        setColumns("identificativo","stato","modello.header");
        setColumnCaption("modello.header","Modello");
    }

}
