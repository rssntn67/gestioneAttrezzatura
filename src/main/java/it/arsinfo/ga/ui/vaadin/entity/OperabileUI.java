package it.arsinfo.ga.ui.vaadin.entity;

import com.vaadin.server.VaadinRequest;

import it.arsinfo.ga.model.entity.Modello;
import it.arsinfo.ga.model.entity.Operabile;
import it.arsinfo.ga.model.entity.Operazione;
import it.arsinfo.ga.ui.AbstractUI;

public abstract class OperabileUI<K extends Modello,T extends Operabile<K>,S extends Operazione<T>> extends AbstractUI {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;
    
    protected void init(VaadinRequest request,Add<T> add ,Search<T> search,OperabileEditor<K,T,S> editor,CustomGrid<T> grid,CustomGrid<S> operGrid,String header) {
        super.init(request, header);
        editor.setVisible(false);
        operGrid.setVisible(false);

        add.setChangeHandler(() -> {
            setHeader(header+":Nuovo");
            hideMenu();
            add.setVisible(false);
            search.setVisible(false);
            grid.setVisible(false);
            editor.edit(add.generate());
            operGrid.setVisible(false);
        });

        search.setChangeHandler(() -> {
            grid.populate(search.find());
        });
        
        operGrid.setChangeHandler(() ->{});
        
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
            operGrid.populate(editor.findOperazioni());
        });

        editor.setChangeHandler(() -> {
            grid.populate(search.find());
            editor.setVisible(false);
            operGrid.setVisible(false);
            setHeader(header);
            showMenu();
            add.setVisible(true);
            search.setVisible(true);
        });

    }    
}
