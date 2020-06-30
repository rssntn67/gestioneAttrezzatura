package it.arsinfo.ga.vaadin;

import com.vaadin.ui.HorizontalLayout;

import it.arsinfo.ga.entity.EntityBase;

public abstract class Editor<T extends EntityBase>
        extends UIChangeHandler {

    	private HorizontalLayout actions = new HorizontalLayout();

		public abstract void edit(T c);
		
	    public HorizontalLayout getActions() {
	        return actions;
	    }

}
