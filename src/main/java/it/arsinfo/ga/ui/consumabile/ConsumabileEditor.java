package it.arsinfo.ga.ui.consumabile;

import java.util.EnumSet;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.ModelloConsumabile;
import it.arsinfo.ga.service.ConsumabileService;
import it.arsinfo.ga.ui.vaadin.entity.OperabileEditor;

public class ConsumabileEditor extends OperabileEditor<Consumabile> {

    private final ComboBox<ModelloConsumabile> modello = new ComboBox<ModelloConsumabile>("Modello");
    private final ComboBox<StatoOperabile> stato = new ComboBox<StatoOperabile>("Stato",
                                                                           EnumSet.allOf(StatoOperabile.class));
    private final TextField identificativo = new TextField("Identificativo");
    private final TextField numero = new TextField("Qu.ta");
    private final TextField disponibili = new TextField("Disponibili");
    private final TextField utilizzati = new TextField("Utilizzati");

    public ConsumabileEditor(ConsumabileService dao) {
        super(dao, new Binder<>(Consumabile.class));

        HorizontalLayout intestazioni = new HorizontalLayout(identificativo,stato);
        intestazioni.addComponentsAndExpand(modello);
        
        HorizontalLayout importi = new HorizontalLayout(numero,utilizzati,disponibili);
        HorizontalLayout code = new HorizontalLayout(getQrCodeImage(),getBarCodeImage());							
        setComponents(getActions(), intestazioni,importi,code);


        getBinder().forField(identificativo).asRequired();
        getBinder().forField(stato).asRequired();
        getBinder()
        .forField(numero)
        .withConverter(new StringToIntegerConverter("Deve essere un numero"))
        .withValidator(num -> num != null && num > 0,"deve essere maggiore di 0")
        .bind(Consumabile::getNumero, Consumabile::setNumero);

        getBinder()
        .forField(disponibili)
        .withConverter(new StringToIntegerConverter("Deve essere un numero"))
        .bind("disponibili");

        getBinder()
        .forField(utilizzati)
        .withConverter(new StringToIntegerConverter("Deve essere un numero"))
        .bind("utilizzati");

        getBinder().bindInstanceFields(this);

        // Configure and style components
        modello.setItemCaptionGenerator(ModelloConsumabile::getHeader);
        modello.setEmptySelectionAllowed(false);
        modello.setItems(dao.getModelli());
        stato.setEmptySelectionAllowed(false);
        
        disponibili.setReadOnly(true);
        
    }

    @Override
    public void focus(boolean persisted, Consumabile c) {
        if (persisted) {
        	numero.setReadOnly(true);;
        	disponibili.setVisible(true);
        	stato.setVisible(true);
        	identificativo.setReadOnly(true);
        	stato.focus();
        	modello.setReadOnly(true);
        } else {
        	numero.setReadOnly(false);;
        	disponibili.setVisible(false);
        	stato.setVisible(false);        	
        	identificativo.setReadOnly(false);
        	identificativo.focus();
        	modello.setReadOnly(false);
        }
    }
}
