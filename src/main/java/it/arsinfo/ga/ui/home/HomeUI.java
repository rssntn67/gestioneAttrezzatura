package it.arsinfo.ga.ui.home;



import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;

import it.arsinfo.ga.ui.AbstractUI;

@SpringUI(path=AbstractUI.HOME)
@Title("Gestione Abbonamenti ADP")
public class HomeUI extends AbstractUI {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;

    @Override
    protected void init(VaadinRequest request) {
    	super.init(request, "");
        
        addComponents(new Label("<hr style=\"height:60px;border-width:0;color:gray;background-color:gray\">",ContentMode.HTML));
        addComponents(new Label("<p style=\"color:black;font-size:20px;face=Arial;\"><b>Benvenuto nel programma gestione attrezzature</b></p>",ContentMode.HTML));

    }

}
