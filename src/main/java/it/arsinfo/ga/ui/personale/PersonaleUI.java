package it.arsinfo.ga.ui.personale;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.dao.PersonaleServiceDao;
import it.arsinfo.ga.entity.Personale;
import it.arsinfo.ga.ui.EditorUI;

@SpringUI(path = EditorUI.URL_PERSONALE)
@Title(EditorUI.TITLE_PERSONALE)
public class PersonaleUI extends EditorUI<Personale> {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Autowired
    PersonaleServiceDao serviceDao;

    @Override
    protected void init(VaadinRequest request) {
        PersonaleAdd add = new PersonaleAdd("Aggiungi Personale");
        PersonaleSearch search = new PersonaleSearch(serviceDao);
        PersonaleGrid grid = new PersonaleGrid("Consumabili");
        PersonaleEditor editor = new PersonaleEditor(serviceDao);
        super.init(request,add,search,editor,grid, "Personale");        
        
        addSmdComponents(editor, 
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
