package it.arsinfo.ga.ui.modello.personale;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.service.ModelloPersonaleService;
import it.arsinfo.ga.ui.vaadin.entity.EntityBaseUI;

@SpringUI(path = EntityBaseUI.URL_MODELLO_PERSONALE)
@Title(EntityBaseUI.TITLE_MODELLO_PERSONALE)
public class ModelloPersonaleUI extends EntityBaseUI<ModelloPersonale> {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Autowired
    ModelloPersonaleService serviceDao;

    @Override
    protected void init(VaadinRequest request) {
        ModelloPersonaleAdd add = new ModelloPersonaleAdd("Aggiungi Modello Personale");
        ModelloPersonaleSearch search = new ModelloPersonaleSearch(serviceDao);
        ModelloPersonaleGrid grid = new ModelloPersonaleGrid("Modelli Personale");
        ModelloPersonaleEditor editor = new ModelloPersonaleEditor(serviceDao);
        super.init(request,add,search,editor,grid, "Modello Personale");        
        
        addComponents(editor, 
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
