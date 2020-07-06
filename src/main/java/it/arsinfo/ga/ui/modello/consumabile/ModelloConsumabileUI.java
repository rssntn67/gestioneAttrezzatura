package it.arsinfo.ga.ui.modello.consumabile;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.ModelloConsumabile;
import it.arsinfo.ga.service.ModelloConsumabileService;
import it.arsinfo.ga.ui.EditorUI;

@SpringUI(path = EditorUI.URL_MODELLO_CONSUMABILE)
@Title(EditorUI.TITLE_MODELLO_CONSUMABILE)
public class ModelloConsumabileUI extends EditorUI<ModelloConsumabile> {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Autowired
    ModelloConsumabileService serviceDao;

    @Override
    protected void init(VaadinRequest request) {
        ModelloConsumabileAdd add = new ModelloConsumabileAdd("Aggiungi Modello Consumabile");
        ModelloConsumabileSearch search = new ModelloConsumabileSearch(serviceDao);
        ModelloConsumabileGrid grid = new ModelloConsumabileGrid("Modelli Consumabile");
        ModelloConsumabileEditor editor = new ModelloConsumabileEditor(serviceDao);
        super.init(request,add,search,editor,grid, "Modello Consumabile");        
        
        addSmdComponents(editor, 
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
