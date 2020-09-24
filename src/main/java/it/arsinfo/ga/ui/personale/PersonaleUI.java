package it.arsinfo.ga.ui.personale;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.model.entity.OperazionePersonale;
import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.service.PersonaleService;
import it.arsinfo.ga.ui.operazione.personale.OperazionePersonaleGrid;
import it.arsinfo.ga.ui.vaadin.entity.EntityBaseUI;
import it.arsinfo.ga.ui.vaadin.entity.OperabileUI;

@SpringUI(path = EntityBaseUI.URL_PERSONALE)
@Title(EntityBaseUI.TITLE_PERSONALE)
public class PersonaleUI extends OperabileUI<ModelloPersonale,Personale,OperazionePersonale> {

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
        OperazionePersonaleGrid opPeGrid = new OperazionePersonaleGrid("Operazioni/Personale");

        super.init(request,add,search,editor,grid, opPeGrid,"Personale");        
        
        addComponents(editor,
        		opPeGrid,
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
