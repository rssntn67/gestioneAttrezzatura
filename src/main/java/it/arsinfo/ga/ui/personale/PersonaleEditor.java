package it.arsinfo.ga.ui.personale;

import java.util.EnumSet;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.service.PersonaleService;
import it.arsinfo.ga.ui.vaadin.Editor;

public class PersonaleEditor extends Editor<Personale> {

    private final ComboBox<ModelloPersonale> modello = new ComboBox<ModelloPersonale>("Modello");
    private final ComboBox<StatoOperabile> stato = new ComboBox<StatoOperabile>("Stato",
                                                                           EnumSet.allOf(StatoOperabile.class));
    private final TextField identificativo = new TextField("Identificativo");
    private final TextField numero = new TextField("Qu.ta");
    private final TextField disponibili = new TextField("Disponibili");
    private final TextField utilizzati = new TextField("Utilizzati");

    public PersonaleEditor(PersonaleService dao) {
        super(dao, new Binder<>(Personale.class));

        HorizontalLayout intestazioni = new HorizontalLayout(identificativo,stato);
        intestazioni.addComponentsAndExpand(modello);
        
        HorizontalLayout importi = new HorizontalLayout(numero,utilizzati,disponibili);
        setComponents(getActions(), intestazioni,importi);


        getBinder().forField(identificativo).asRequired();
        getBinder().forField(stato).asRequired();
        getBinder()
        .forField(numero)
        .withConverter(new StringToIntegerConverter("Deve essere un numero"))
        .withValidator(num -> num != null && num > 0,"deve essere maggiore di 0")
        .bind(Personale::getNumero, Personale::setNumero);

        getBinder()
        .forField(utilizzati)
        .withConverter(new StringToIntegerConverter("Deve essere un numero"))
        .bind(Personale::getUtilizzati, Personale::setUtilizzati);
        getBinder()
        .forField(disponibili)
        .withConverter(new StringToIntegerConverter("Deve essere un numero"))
        .bind("disponibili");

        getBinder().bindInstanceFields(this);

        // Configure and style components
        modello.setItemCaptionGenerator(ModelloPersonale::getHeader);
        modello.setEmptySelectionAllowed(false);
        modello.setItems(dao.getModelli());
        stato.setEmptySelectionAllowed(false);
        
        disponibili.setReadOnly(true);
        utilizzati.setReadOnly(true);
        
    }

    @Override
    public void focus(boolean persisted, Personale c) {
        if (persisted) {
        	numero.setReadOnly(true);
        	disponibili.setVisible(true);
        	utilizzati.setVisible(true);
        	stato.setVisible(true);
        	identificativo.setReadOnly(true);
        	stato.focus();
        	modello.setReadOnly(true);
        } else {
        	numero.setReadOnly(false);;
        	disponibili.setVisible(false);
        	utilizzati.setVisible(false);
        	stato.setVisible(false);        	
        	identificativo.setReadOnly(false);
        	identificativo.focus();
        	modello.setReadOnly(false);
        }
    }
}
