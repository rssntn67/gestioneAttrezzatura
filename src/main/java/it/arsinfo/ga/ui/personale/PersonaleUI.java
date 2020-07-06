package it.arsinfo.ga.ui.personale;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.service.PersonaleService;
import it.arsinfo.ga.ui.vaadin.entity.EntityBaseUI;

@SpringUI(path = EntityBaseUI.URL_PERSONALE)
@Title(EntityBaseUI.TITLE_PERSONALE)
public class PersonaleUI extends EntityBaseUI<Personale> {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Autowired
    PersonaleService serviceDao;

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
