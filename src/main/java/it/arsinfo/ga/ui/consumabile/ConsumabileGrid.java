package it.arsinfo.ga.ui.consumabile;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.entity.Consumabile;
import it.arsinfo.ga.vaadin.CustomGrid;

public class ConsumabileGrid extends CustomGrid<Consumabile> {

    public ConsumabileGrid(String gridName) {
        super(new Grid<>(Consumabile.class),gridName);
        setColumns("identificativo","stato","modello.header");
        setColumnCaption("modello.header","Modello");
    }

}
