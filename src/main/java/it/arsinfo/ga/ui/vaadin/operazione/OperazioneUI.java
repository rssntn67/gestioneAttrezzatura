package it.arsinfo.ga.ui.vaadin.operazione;

import com.vaadin.server.VaadinRequest;

import it.arsinfo.ga.model.entity.Modello;
import it.arsinfo.ga.model.entity.Operabile;
import it.arsinfo.ga.model.entity.Operazione;
import it.arsinfo.ga.ui.AbstractUI;
import it.arsinfo.ga.ui.vaadin.entity.CustomGrid;

public abstract class OperazioneUI<K extends Modello,T extends Operabile<K>, S extends Operazione<K, T>> extends AbstractUI {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;
    
    protected void init(VaadinRequest request,
    		OperazioneAdd<K,T,S> add ,
    		OperazioneSearch<K,T,S> search ,
    		OperazioneEditor<K,T,S> editor,
    		CustomGrid<S> grid,
    		String header) {
        super.init(request, header);
        editor.setVisible(false);

        add.setChangeHandler(() -> {
            setHeader(header+":Nuova");
            hideMenu();
            add.setVisible(false);
            search.setVisible(false);
            editor.edit(add.generate());
        });
        
        search.setChangeHandler(() -> {
        	grid.populate(search.find());        	
        });
        
        grid.setChangeHandler(() -> {
        });
        
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            setHeader(header);
            showMenu();
            add.setVisible(true);
            search.setVisible(true);
            grid.populate(search.find());
        });

    }    
}
