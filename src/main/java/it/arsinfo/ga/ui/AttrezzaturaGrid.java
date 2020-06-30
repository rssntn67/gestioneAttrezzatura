package it.arsinfo.ga.ui;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.entity.Attrezzatura;
import it.arsinfo.ga.vaadin.CustomGrid;

public class AttrezzaturaGrid extends CustomGrid<Attrezzatura> {

    public AttrezzaturaGrid(String gridName) {
        super(new Grid<>(Attrezzatura.class),gridName);
        setColumns("identificativo","statoAttrezzatura","modello");
        setColumnCaption("statoAttrezzatura","Stato");
    }

}
