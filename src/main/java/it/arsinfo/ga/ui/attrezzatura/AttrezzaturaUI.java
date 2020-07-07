package it.arsinfo.ga.ui.attrezzatura;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.service.AttrezzaturaService;
import it.arsinfo.ga.ui.vaadin.entity.EntityBaseUI;

@SpringUI(path = EntityBaseUI.URL_ATTREZZATURA)
@Title(EntityBaseUI.TITLE_ATTREZZATURA)
public class AttrezzaturaUI extends EntityBaseUI<Attrezzatura> {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Autowired
    AttrezzaturaService serviceDao;

    @Override
    protected void init(VaadinRequest request) {
        AttrezzaturaAdd add = new AttrezzaturaAdd("Aggiungi Attrezzatura");
        AttrezzaturaSearch search = new AttrezzaturaSearch(serviceDao);
        AttrezzaturaGrid grid = new AttrezzaturaGrid("Attrezzature");
        AttrezzaturaEditor editor = new AttrezzaturaEditor(serviceDao);
        super.init(request,add,search,editor,grid, "Attrezzatura");        
        
        addComponents(editor, 
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
