package it.arsinfo.ga.ui.attrezzatura;

import java.util.EnumSet;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.dao.AttrezzaturaServiceDao;
import it.arsinfo.ga.data.StatoAttrezzatura;
import it.arsinfo.ga.entity.Attrezzatura;
import it.arsinfo.ga.entity.ModelloAttrezzatura;
import it.arsinfo.ga.vaadin.EntityEditor;

public class AttrezzaturaEditor extends EntityEditor<Attrezzatura> {

    private final ComboBox<ModelloAttrezzatura> modelloAttrezzatura = new ComboBox<ModelloAttrezzatura>("Modello");
    private final ComboBox<StatoAttrezzatura> statoAttrezzatura = new ComboBox<StatoAttrezzatura>("Stato",
                                                                           EnumSet.allOf(StatoAttrezzatura.class));
    private final TextField identificativo = new TextField("Identificativo");
    private final TextField valore = new TextField("Valore");
    private final TextField speseManutenzione = new TextField("Spese Manutenzione");
    private final TextField speseRiparazione = new TextField("Spese Riparazione");

    public AttrezzaturaEditor(AttrezzaturaServiceDao dao) {
        super(dao, new Binder<>(Attrezzatura.class));

        HorizontalLayout intestazioni = new HorizontalLayout(identificativo,statoAttrezzatura);
        intestazioni.addComponentsAndExpand(modelloAttrezzatura);
        
        HorizontalLayout importi = new HorizontalLayout(valore,speseManutenzione,speseRiparazione);
        setComponents(getActions(), intestazioni,importi);


        getBinder().forField(identificativo).asRequired();
        getBinder().forField(statoAttrezzatura).asRequired();
        getBinder()
        .forField(valore)
        .withConverter(new StringToBigDecimalConverter("Conversione in Eur")).bind("valore");
        getBinder()
        .forField(speseManutenzione)
        .withConverter(new StringToBigDecimalConverter("Conversione in Eur")).bind("speseManutenzione");
        getBinder()
        .forField(speseRiparazione)
        .withConverter(new StringToBigDecimalConverter("Conversione in Eur")).bind("speseRiparazione");

        getBinder().bindInstanceFields(this);

        // Configure and style components
        modelloAttrezzatura.setItemCaptionGenerator(ModelloAttrezzatura::getHeader);
        modelloAttrezzatura.setEmptySelectionAllowed(false);
        modelloAttrezzatura.setItems(dao.getModelli());
        statoAttrezzatura.setEmptySelectionAllowed(false);
        statoAttrezzatura.setVisible(false);
        
    }

    @Override
    public void focus(boolean persisted, Attrezzatura c) {
        if (persisted) {
        	statoAttrezzatura.setVisible(true);
        	identificativo.setReadOnly(true);
        	statoAttrezzatura.focus();
        	modelloAttrezzatura.setReadOnly(true);
        } else {
        	statoAttrezzatura.setVisible(false);        	
        	identificativo.setReadOnly(false);
        	identificativo.focus();
        	modelloAttrezzatura.setReadOnly(false);
        }
    }
}
