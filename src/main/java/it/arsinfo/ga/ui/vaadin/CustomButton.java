package it.arsinfo.ga.ui.vaadin;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;

public class CustomButton extends UIChangeHandler {

    private final Button button;

    public CustomButton(String caption, VaadinIcons icon) {
        button = new Button(caption, icon);
        button.addClickListener(e -> onChange());
        setComponents(button);

    }
    
    public Button getButton() {
        return button;
    }
    
    public void enable() {
    	button.setEnabled(true);
    }
    
    public void disable() {
    	button.setEnabled(false);
    }
    

}
