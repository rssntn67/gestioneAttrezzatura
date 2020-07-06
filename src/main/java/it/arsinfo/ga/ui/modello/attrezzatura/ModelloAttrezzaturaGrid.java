package it.arsinfo.ga.ui.modello.attrezzatura;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.vaadin.CustomGrid;

public class ModelloAttrezzaturaGrid extends CustomGrid<ModelloAttrezzatura> {

    public ModelloAttrezzaturaGrid(String gridName) {
        super(new Grid<>(ModelloAttrezzatura.class),gridName);
        setColumns("nome","marca","tipo","fornitore","annoProduzione","costo");
    }

}
