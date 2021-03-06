package it.arsinfo.ga.ui.modello.personale;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.ui.vaadin.entity.CustomGrid;

public class ModelloPersonaleGrid extends CustomGrid<ModelloPersonale> {

    public ModelloPersonaleGrid(String gridName) {
        super(new Grid<>(ModelloPersonale.class),gridName);
        setColumns("nome","tipo","fornitore","annoProduzione","costo");
    }

}
