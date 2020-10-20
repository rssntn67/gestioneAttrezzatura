package it.arsinfo.ga.ui.operazione.consumabile;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.model.entity.OperazioneConsumabile;
import it.arsinfo.ga.ui.vaadin.entity.CustomGrid;

public class OperazioneConsumabileGrid extends CustomGrid<OperazioneConsumabile> {

    public OperazioneConsumabileGrid(String gridName) {
        super(new Grid<>(OperazioneConsumabile.class),gridName);
        setColumns("dataOperazione","cantiere.header","operatore.identificativo","operabile.header","tipoOperazione","numero");
        setColumnCaption("cantiere.header", "Cantiere");
        setColumnCaption("operabile.headers", "Consumabile");
        setColumnCaption("operatore.identificativo", "Operatore");
        setColumnCaption("numero", "qu.tà");
    }

}
