package it.arsinfo.ga.ui.operazione.attrezzatura;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.ui.vaadin.entity.CustomGrid;

public class OperazioneAttrezzaturaGrid extends CustomGrid<OperazioneAttrezzatura> {

    public OperazioneAttrezzaturaGrid(String gridName) {
        super(new Grid<>(OperazioneAttrezzatura.class),gridName);
        setColumns("dataOperazione","operatore.identificativo","cantiere.header","operabile.header","tipoOperazione");
        setColumnCaption("cantiere.header", "Cantiere");
        setColumnCaption("operatore.identificativo", "Operatore");
        setColumnCaption("operabile.header", "Attrezzatura");
    }

}
