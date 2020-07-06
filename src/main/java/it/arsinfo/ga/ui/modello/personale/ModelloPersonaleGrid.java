package it.arsinfo.ga.ui.modello.personale;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.entity.ModelloPersonale;
import it.arsinfo.ga.vaadin.CustomGrid;

public class ModelloPersonaleGrid extends CustomGrid<ModelloPersonale> {

    public ModelloPersonaleGrid(String gridName) {
        super(new Grid<>(ModelloPersonale.class),gridName);
        setColumns("nome","tipo","fornitore","annoProduzione","costo");
    }

}
