package it.arsinfo.ga.ui.cantiere;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.ui.vaadin.entity.CustomGrid;

public class CantiereGrid extends CustomGrid<Cantiere> {

    public CantiereGrid(String gridName) {
        super(new Grid<>(Cantiere.class),gridName);
    }

}
