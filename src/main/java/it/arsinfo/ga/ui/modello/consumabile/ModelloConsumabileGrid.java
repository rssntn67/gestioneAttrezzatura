package it.arsinfo.ga.ui.modello.consumabile;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.entity.ModelloConsumabile;
import it.arsinfo.ga.vaadin.CustomGrid;

public class ModelloConsumabileGrid extends CustomGrid<ModelloConsumabile> {

    public ModelloConsumabileGrid(String gridName) {
        super(new Grid<>(ModelloConsumabile.class),gridName);
        setColumns("nome","marca","tipo","fornitore","annoProduzione","costo");
    }

}
