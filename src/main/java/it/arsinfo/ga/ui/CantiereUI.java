package it.arsinfo.ga.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.dao.CantiereServiceDao;
import it.arsinfo.ga.entity.Cantiere;
import it.arsinfo.ga.vaadin.EditorUI;

@SpringUI(path = EditorUI.URL_CANTIERE)
@Title(EditorUI.TITLE_CANTIERE)
public class CantiereUI extends EditorUI<Cantiere> {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Autowired
    CantiereServiceDao serviceDao;

    @Override
    protected void init(VaadinRequest request) {
        CantiereAdd add = new CantiereAdd("Aggiungi Cantiere");
        CantiereSearch search = new CantiereSearch(serviceDao);
        CantiereGrid grid = new CantiereGrid("Cantieri");
        CantiereEditor editor = new CantiereEditor(serviceDao);
        super.init(request,add,search,editor,grid, "Cantiere");        
        
        addSmdComponents(editor, 
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
