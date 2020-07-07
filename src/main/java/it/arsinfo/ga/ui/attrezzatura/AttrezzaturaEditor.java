package it.arsinfo.ga.ui.attrezzatura;

import java.util.EnumSet;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.service.AttrezzaturaService;
import it.arsinfo.ga.ui.vaadin.entity.Editor;

public class AttrezzaturaEditor extends Editor<Attrezzatura> {

    private final ComboBox<ModelloAttrezzatura> modello = new ComboBox<ModelloAttrezzatura>("Modello");
    private final ComboBox<StatoOperabile> stato = new ComboBox<StatoOperabile>("Stato",
                                                                           EnumSet.allOf(StatoOperabile.class));
    private final TextField identificativo = new TextField("Identificativo");
    private final TextField speseManutenzione = new TextField("Spese Manutenzione");
    private final TextField speseRiparazione = new TextField("Spese Riparazione");

    public AttrezzaturaEditor(AttrezzaturaService dao) {
        super(dao, new Binder<>(Attrezzatura.class));

        HorizontalLayout intestazioni = new HorizontalLayout(identificativo,stato);
        intestazioni.addComponentsAndExpand(modello);
        
        HorizontalLayout importi = new HorizontalLayout(speseManutenzione,speseRiparazione);
        setComponents(getActions(), intestazioni,importi,getImage());


        getBinder().forField(identificativo).asRequired();
        getBinder().forField(stato).asRequired();
        getBinder()
        .forField(speseManutenzione)
        .withConverter(new StringToBigDecimalConverter("Conversione in Eur")).bind("speseManutenzione");
        getBinder()
        .forField(speseRiparazione)
        .withConverter(new StringToBigDecimalConverter("Conversione in Eur")).bind("speseRiparazione");

        getBinder().bindInstanceFields(this);

        // Configure and style components
        modello.setItemCaptionGenerator(ModelloAttrezzatura::getHeader);
        modello.setEmptySelectionAllowed(false);
        modello.setItems(dao.getModelli());
        stato.setEmptySelectionAllowed(false);
        stato.setVisible(false);
        
    }

    @Override
    public void focus(boolean persisted, Attrezzatura c) {
        if (persisted) {
        	stato.setVisible(true);
        	identificativo.setReadOnly(true);
        	stato.focus();
        	modello.setReadOnly(true);
        } else {
        	stato.setVisible(false);        	
        	identificativo.setReadOnly(false);
        	identificativo.focus();
        	modello.setReadOnly(false);
        }
    }
}
