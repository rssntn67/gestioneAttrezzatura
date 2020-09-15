package it.arsinfo.ga.ui.operazione.personale;

import com.vaadin.ui.Grid;

import it.arsinfo.ga.model.entity.OperazionePersonale;
import it.arsinfo.ga.ui.vaadin.entity.CustomGrid;

public class OperazionePersonaleGrid extends CustomGrid<OperazionePersonale> {

    public OperazionePersonaleGrid(String gridName) {
        super(new Grid<>(OperazionePersonale.class),gridName);
        setColumns("dataOperazione","cantiere.header","operabile.header","tipoOperazione","numero");
        setColumnCaption("cantiere.header", "Cantiere");
        setColumnCaption("operabile.code", "Personale");
        setColumnCaption("numero", "qu.tà");
    }

}
