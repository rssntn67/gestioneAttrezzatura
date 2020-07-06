package it.arsinfo.ga.ui.consumabile;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.dao.ConsumabileServiceDao;
import it.arsinfo.ga.entity.Consumabile;
import it.arsinfo.ga.ui.EditorUI;

@SpringUI(path = EditorUI.URL_CONSUMABILE)
@Title(EditorUI.TITLE_CONSUMABILE)
public class ConsumabileUI extends EditorUI<Consumabile> {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Autowired
    ConsumabileServiceDao serviceDao;

    @Override
    protected void init(VaadinRequest request) {
        ConsumabileAdd add = new ConsumabileAdd("Aggiungi Consumabile");
        ConsumabileSearch search = new ConsumabileSearch(serviceDao);
        ConsumabileGrid grid = new ConsumabileGrid("Consumabili");
        ConsumabileEditor editor = new ConsumabileEditor(serviceDao);
        super.init(request,add,search,editor,grid, "Consumabile");        
        
        addSmdComponents(editor, 
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
