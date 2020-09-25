package it.arsinfo.ga.ui.operatore;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.ui.vaadin.entity.CustomGrid;

public class OperatoreGrid extends CustomGrid<Operatore> {

    public OperatoreGrid(String gridName) {
        super(new Grid<>(Operatore.class),gridName);
    }

}
