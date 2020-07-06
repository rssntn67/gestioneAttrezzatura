package it.arsinfo.ga.ui.cantiere;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.service.CantiereService;
import it.arsinfo.ga.ui.EditorUI;

@SpringUI(path = EditorUI.URL_CANTIERE)
@Title(EditorUI.TITLE_CANTIERE)
public class CantiereUI extends EditorUI<Cantiere> {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Autowired
    CantiereService serviceDao;

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
