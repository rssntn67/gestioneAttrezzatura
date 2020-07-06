package it.arsinfo.ga.ui.consumabile;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.vaadin.CustomGrid;

public class ConsumabileGrid extends CustomGrid<Consumabile> {

    public ConsumabileGrid(String gridName) {
        super(new Grid<>(Consumabile.class),gridName);
        setColumns("identificativo","stato","modello.header","numero","utilizzati","disponibili");
        setColumnCaption("modello.header","Modello");
    }

}
