package it.arsinfo.ga.ui;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.entity.ModelloAttrezzatura;
import it.arsinfo.ga.vaadin.CustomGrid;

public class ModelloAttrezzaturaGrid extends CustomGrid<ModelloAttrezzatura> {

    public ModelloAttrezzaturaGrid(String gridName) {
        super(new Grid<>(ModelloAttrezzatura.class),gridName);
    }

}
