package it.arsinfo.ga.ui.attrezzatura;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.service.AttrezzaturaService;
import it.arsinfo.ga.ui.EditorUI;

@SpringUI(path = EditorUI.URL_ATTREZZATURA)
@Title(EditorUI.TITLE_ATTREZZATURA)
public class AttrezzaturaUI extends EditorUI<Attrezzatura> {

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
        
        addSmdComponents(editor, 
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
