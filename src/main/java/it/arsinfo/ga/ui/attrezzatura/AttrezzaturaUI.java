package it.arsinfo.ga.ui.attrezzatura;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.service.AttrezzaturaService;
import it.arsinfo.ga.ui.operazione.attrezzatura.OperazioneAttrezzaturaGrid;
import it.arsinfo.ga.ui.vaadin.entity.EntityBaseUI;
import it.arsinfo.ga.ui.vaadin.entity.OperabileUI;

@SpringUI(path = EntityBaseUI.URL_ATTREZZATURA)
@Title(EntityBaseUI.TITLE_ATTREZZATURA)
public class AttrezzaturaUI extends OperabileUI<ModelloAttrezzatura, Attrezzatura,OperazioneAttrezzatura> {

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
        OperazioneAttrezzaturaGrid opAtGrid = new OperazioneAttrezzaturaGrid("Operazioni/Attrezzatura");

        super.init(request,add,search,editor,grid, opAtGrid,"Attrezzatura");        
        
        addComponents(editor,
        		opAtGrid,
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
