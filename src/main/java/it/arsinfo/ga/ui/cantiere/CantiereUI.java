package it.arsinfo.ga.ui.cantiere;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.service.CantiereService;
import it.arsinfo.ga.ui.operazione.attrezzatura.OperazioneAttrezzaturaGrid;
import it.arsinfo.ga.ui.operazione.consumabile.OperazioneConsumabileGrid;
import it.arsinfo.ga.ui.operazione.personale.OperazionePersonaleGrid;
import it.arsinfo.ga.ui.vaadin.entity.EntityBaseUI;

@SpringUI(path = EntityBaseUI.URL_CANTIERE)
@Title(EntityBaseUI.TITLE_CANTIERE)
public class CantiereUI extends EntityBaseUI<Cantiere> {

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
        OperazioneAttrezzaturaGrid opAtGrid = new OperazioneAttrezzaturaGrid("Operazioni/Attrezzatura");
        OperazioneConsumabileGrid opCoGrid = new OperazioneConsumabileGrid("Operazioni/Consumabile");
        OperazionePersonaleGrid opPeGrid = new OperazionePersonaleGrid("Operazioni/Personale");

        opAtGrid.setChangeHandler(()->{});
        opCoGrid.setChangeHandler(()->{});
        opPeGrid.setChangeHandler(()->{});
        
        super.init(request,add,search,editor,grid, "Cantiere");        
        
        String header = "Cantiere";
        add.setChangeHandler(() -> {
            setHeader(header+":Nuovo");
            hideMenu();
            add.setVisible(false);
            search.setVisible(false);
            grid.setVisible(false);
            editor.edit(add.generate());
            opAtGrid.setVisible(false);
            opCoGrid.setVisible(false);
            opPeGrid.setVisible(false);
        });

        search.setChangeHandler(() -> {
            grid.populate(search.find());
        });
        
        grid.setChangeHandler(() -> {
            if (grid.getSelected() == null) {
                return;
            }
            setHeader(header+":Edit:"+grid.getSelected().getHeader());
            hideMenu();
            add.setVisible(false);
            search.setVisible(false);
            grid.setVisible(false);
            editor.edit(grid.getSelected());
            opAtGrid.populate(serviceDao.findOperazioneAttrezzatura(grid.getSelected()));;
            opCoGrid.populate(serviceDao.findOperazioneConsumabile(grid.getSelected()));;
            opPeGrid.populate(serviceDao.findOperazionePersonale(grid.getSelected()));;
        });

        editor.setChangeHandler(() -> {
            grid.populate(search.find());
            opAtGrid.setVisible(false);
            opCoGrid.setVisible(false);
            opPeGrid.setVisible(false);
            editor.setVisible(false);
            setHeader(header);
            showMenu();
            add.setVisible(true);
            search.setVisible(true);
        });

        
        addComponents(editor,
        		opAtGrid,
        		opCoGrid,
        		opPeGrid,
                add,
                search, 
                grid);

        editor.setVisible(false);
        opAtGrid.setVisible(false);
        opCoGrid.setVisible(false);
        opPeGrid.setVisible(false);
        
        grid.populate(search.findAll());

    }
    
}
