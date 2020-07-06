package it.arsinfo.ga.ui.modello.personale;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.dao.ModelloPersonaleServiceDao;
import it.arsinfo.ga.entity.ModelloPersonale;
import it.arsinfo.ga.ui.EditorUI;

@SpringUI(path = EditorUI.URL_MODELLO_PERSONALE)
@Title(EditorUI.TITLE_MODELLO_PERSONALE)
public class ModelloPersonaleUI extends EditorUI<ModelloPersonale> {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Autowired
    ModelloPersonaleServiceDao serviceDao;

    @Override
    protected void init(VaadinRequest request) {
        ModelloPersonaleAdd add = new ModelloPersonaleAdd("Aggiungi Modello Personale");
        ModelloPersonaleSearch search = new ModelloPersonaleSearch(serviceDao);
        ModelloPersonaleGrid grid = new ModelloPersonaleGrid("Modelli Personale");
        ModelloPersonaleEditor editor = new ModelloPersonaleEditor(serviceDao);
        super.init(request,add,search,editor,grid, "Modello Personale");        
        
        addSmdComponents(editor, 
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
