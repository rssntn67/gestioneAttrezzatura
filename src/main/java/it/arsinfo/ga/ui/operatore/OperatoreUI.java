package it.arsinfo.ga.ui.operatore;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.service.OperatoreService;
import it.arsinfo.ga.ui.vaadin.entity.EntityBaseUI;

@SpringUI(path = EntityBaseUI.URL_OPERATORE)
@Title(EntityBaseUI.TITLE_OPERATORE)
public class OperatoreUI extends EntityBaseUI<Operatore> {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Autowired
    OperatoreService serviceDao;

    @Override
    protected void init(VaadinRequest request) {
        OperatoreAdd add = new OperatoreAdd("Aggiungi Operatore");
        OperatoreSearch search = new OperatoreSearch(serviceDao);
        OperatoreGrid grid = new OperatoreGrid("Operatori");
        OperatoreEditor editor = new OperatoreEditor(serviceDao);
        
        super.init(request,add,search,editor,grid, "Operatore");        
        
        addComponents(editor,
                add,
                search, 
                grid);
        
        grid.populate(search.findAll());

    }
    
}
