package it.arsinfo.ga.ui.operatore;

import java.util.EnumSet;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.StatoOperatore;
import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.service.OperatoreService;
import it.arsinfo.ga.ui.vaadin.entity.Editor;

public class OperatoreEditor extends Editor<Operatore> {

    private final ComboBox<StatoOperatore> stato 
    	= new ComboBox<StatoOperatore>("Stato",EnumSet.allOf(StatoOperatore.class));
    private final TextField identificativo = new TextField("Identificativo");
    private final TextField apikey = new TextField("Api Key");
    private final TextField email = new TextField("EMail");
    private final TextField telefono = new TextField("Tel.");
 
    public OperatoreEditor(OperatoreService dao) {
        super(dao, new Binder<>(Operatore.class));
        
        setComponents(
        		getActions(), 
        		new HorizontalLayout(identificativo,stato,apikey),
        		new HorizontalLayout(email,telefono)
        		);


        getBinder().forField(apikey).asRequired().bind(Operatore::getApikey,Operatore::setApikey);
        getBinder().forField(identificativo).asRequired().bind(Operatore::getIdentificativo,Operatore::setIdentificativo);
        getBinder().forField(stato).asRequired().bind(Operatore::getStato,Operatore::setStato);
        getBinder().forField(email)
        	.withValidator(new EmailValidator("email non valida"))
        	.bind(Operatore::getEmail,Operatore::setEmail);
        getBinder().forField(telefono)
    	.withValidator(new RegexpValidator("telefono non valido", "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"))
    	.bind(Operatore::getTelefono,Operatore::setTelefono);

        getBinder().bindInstanceFields(this);

        // Configure and style components
        stato.setEmptySelectionAllowed(false);
        apikey.setReadOnly(true);
        
    }

    @Override
    public void focus(boolean persisted, Operatore c) {
        if (persisted) {
        	stato.setVisible(true);
        	apikey.setVisible(true);
        	identificativo.setReadOnly(true);
        	stato.focus();
        } else {
        	stato.setVisible(false);        	
            apikey.setVisible(false);
        	identificativo.setReadOnly(false);
        	identificativo.focus();
        }
    }
    
}
