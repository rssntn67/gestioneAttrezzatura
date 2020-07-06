package it.arsinfo.ga.ui.vaadin;

import com.vaadin.server.VaadinRequest;

import it.arsinfo.ga.model.entity.EntityBase;
import it.arsinfo.ga.ui.AbstractUI;

public abstract class EntityBaseUI<T extends EntityBase> extends AbstractUI {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;
    
    protected void init(VaadinRequest request,Add<T> add ,Search<T> search,Editor<T> editor,CustomGrid<T> grid,String header) {
        super.init(request, header);
        editor.setVisible(false);

        add.setChangeHandler(() -> {
            setHeader(header+":Nuovo");
            hideMenu();
            add.setVisible(false);
            search.setVisible(false);
            grid.setVisible(false);
            editor.edit(add.generate());
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
        });

        editor.setChangeHandler(() -> {
            grid.populate(search.find());
            editor.setVisible(false);
            setHeader(header);
            showMenu();
            add.setVisible(true);
            search.setVisible(true);
        });

    }    
}
