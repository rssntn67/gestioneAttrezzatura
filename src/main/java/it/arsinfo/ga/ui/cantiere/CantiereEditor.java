package it.arsinfo.ga.ui.cantiere;

import java.time.ZoneId;
import java.util.EnumSet;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.TipoCantiere;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.service.CantiereService;
import it.arsinfo.ga.ui.vaadin.entity.Editor;

public class CantiereEditor extends Editor<Cantiere> {

    private final ComboBox<StatoCantiere> stato 
    	= new ComboBox<StatoCantiere>("Stato",EnumSet.allOf(StatoCantiere.class));
    private final ComboBox<TipoCantiere> tipo 
       = new ComboBox<TipoCantiere>("Tipo",EnumSet.allOf(TipoCantiere.class));
    private final TextField identificativo = new TextField("Identificativo");
    private final TextField sitoIn = new TextField("Sito In");
    private final TextField distanzaChilometrica = new TextField("distanza in Km");
    private final DateField aperturaCantiere = new DateField("Data apertura Cantiere");
    private final DateField chiusuraCantiere = new DateField("Data chiusura Cantiere");
 
    public CantiereEditor(CantiereService dao) {
        super(dao, new Binder<>(Cantiere.class));
        
        setComponents(
        		getActions(), 
        		new HorizontalLayout(identificativo,stato,tipo),
        		new HorizontalLayout(sitoIn,distanzaChilometrica),
        		new HorizontalLayout(aperturaCantiere,chiusuraCantiere),
        		getQrCodeImage(),getBarCodeImage()
        		);


        getBinder().forField(identificativo).asRequired();
        getBinder().forField(stato).asRequired();
        getBinder()
        	.forField(distanzaChilometrica)
        	.withConverter(new StringToIntegerConverter("deve essere un intero"))
        	.bind(Cantiere::getDistanzaChilometrica,Cantiere::setDistanzaChilometrica);
        getBinder()
        	.forField(aperturaCantiere)
        	.withConverter(new LocalDateToDateConverter(ZoneId.systemDefault()))
        	.bind(Cantiere::getAperturaCantiere,Cantiere::setAperturaCantiere);
        getBinder()
    	.forField(chiusuraCantiere)
    	.withConverter(new LocalDateToDateConverter(ZoneId.systemDefault()))
    	.bind(Cantiere::getChiusuraCantiere,Cantiere::setChiusuraCantiere);

        getBinder().bindInstanceFields(this);

        aperturaCantiere.setDateFormat("dd/MM/yyyy");
        chiusuraCantiere.setDateFormat("dd/MM/yyyy");

        // Configure and style components
        stato.setEmptySelectionAllowed(false);
        stato.setVisible(false);
        tipo.setEmptySelectionAllowed(false);
        
    }

    @Override
    public void focus(boolean persisted, Cantiere c) {
        if (persisted) {
        	stato.setVisible(true);
        	identificativo.setReadOnly(true);
        	stato.focus();
        } else {
        	stato.setVisible(false);        	
        	identificativo.setReadOnly(false);
        	identificativo.focus();
        }
    }
}
