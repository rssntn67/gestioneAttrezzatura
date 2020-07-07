package it.arsinfo.ga.ui.modello.attrezzatura;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.service.ModelloAttrezzaturaService;
import it.arsinfo.ga.ui.vaadin.entity.EntityBaseUI;

@SpringUI(path = EntityBaseUI.URL_MODELLO_ATTREZZATURA)
@Title(EntityBaseUI.TITLE_MODELLO_ATTREZZATURA)
public class ModelloAttrezzaturaUI extends EntityBaseUI<ModelloAttrezzatura> {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Autowired
    ModelloAttrezzaturaService serviceDao;

    @Override
    protected void init(VaadinRequest request) {
        ModelloAttrezzaturaAdd add = new ModelloAttrezzaturaAdd("Aggiungi Modello Attrezzatura");
        ModelloAttrezzaturaSearch search = new ModelloAttrezzaturaSearch(serviceDao);
        ModelloAttrezzaturaGrid grid = new ModelloAttrezzaturaGrid("Modelli Attrezzatura");
        ModelloAttrezzaturaEditor editor = new ModelloAttrezzaturaEditor(serviceDao);
        super.init(request,add,search,editor,grid, "Modello Attrezzatura");        
        
        addComponents(editor, 
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
