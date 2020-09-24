package it.arsinfo.ga.ui.consumabile;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.ModelloConsumabile;
import it.arsinfo.ga.model.entity.OperazioneConsumabile;
import it.arsinfo.ga.service.ConsumabileService;
import it.arsinfo.ga.ui.operazione.consumabile.OperazioneConsumabileGrid;
import it.arsinfo.ga.ui.vaadin.entity.EntityBaseUI;
import it.arsinfo.ga.ui.vaadin.entity.OperabileUI;

@SpringUI(path = EntityBaseUI.URL_CONSUMABILE)
@Title(EntityBaseUI.TITLE_CONSUMABILE)
public class ConsumabileUI extends OperabileUI<ModelloConsumabile,Consumabile,OperazioneConsumabile> {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Autowired
    ConsumabileService serviceDao;

    @Override
    protected void init(VaadinRequest request) {
        ConsumabileAdd add = new ConsumabileAdd("Aggiungi Consumabile");
        ConsumabileSearch search = new ConsumabileSearch(serviceDao);
        ConsumabileGrid grid = new ConsumabileGrid("Consumabili");
        ConsumabileEditor editor = new ConsumabileEditor(serviceDao);
        OperazioneConsumabileGrid opCoGrid = new OperazioneConsumabileGrid("Operazioni/Consumabile");

        super.init(request,add,search,editor,grid,opCoGrid,"Consumabile");        
        
        addComponents(editor, 
        		opCoGrid,
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
