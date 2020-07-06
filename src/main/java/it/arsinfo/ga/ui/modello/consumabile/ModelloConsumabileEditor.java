package it.arsinfo.ga.ui.modello.consumabile;

import java.util.EnumSet;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.MarcaConsumabile;
import it.arsinfo.ga.model.data.TipoConsumabile;
import it.arsinfo.ga.model.entity.ModelloConsumabile;
import it.arsinfo.ga.service.ModelloConsumabileService;
import it.arsinfo.ga.vaadin.EntityEditor;

public class ModelloConsumabileEditor extends EntityEditor<ModelloConsumabile> {

    private final ComboBox<Fornitore> fornitore = new ComboBox<Fornitore>("Fornitore",
            EnumSet.allOf(Fornitore.class));
    private final ComboBox<Anno> annoProduzione = new ComboBox<Anno>("Anno Produzione",
            EnumSet.allOf(Anno.class));
    private final ComboBox<TipoConsumabile> tipo = new ComboBox<TipoConsumabile>("Tipo Consumabile",
                                                                           EnumSet.allOf(TipoConsumabile.class));
    private final ComboBox<MarcaConsumabile> marca = new ComboBox<MarcaConsumabile>("Marca Consumabile",
                                                                                            EnumSet.allOf(MarcaConsumabile.class));
    private final TextField nome = new TextField("Nome");
    private final TextField descrizione = new TextField("Descrizione");
    private final TextField costo = new TextField("costo");

    public ModelloConsumabileEditor(ModelloConsumabileService dao) {
        super(dao, new Binder<>(ModelloConsumabile.class));

        HorizontalLayout intestazioni = new HorizontalLayout(nome,fornitore,annoProduzione);

        HorizontalLayout residenza = new HorizontalLayout(costo,tipo,marca);
        residenza.addComponentsAndExpand(descrizione);
        
        setComponents(getActions(), intestazioni,residenza);


        getBinder().forField(nome).asRequired();
        getBinder().forField(fornitore).asRequired();
        getBinder().forField(annoProduzione).asRequired();
        getBinder()
        .forField(costo)
        .withConverter(new StringToBigDecimalConverter("Conversione in Eur")).bind("costo");

        getBinder().bindInstanceFields(this);

        // Configure and style components
        annoProduzione.setItemCaptionGenerator(Anno::getAnnoAsString);
        annoProduzione.setEmptySelectionAllowed(false);
        
        tipo.setEmptySelectionAllowed(false);
        marca.setEmptySelectionAllowed(false);
        

    }

    @Override
    public void focus(boolean persisted, ModelloConsumabile c) {
    	if (persisted) {
    		costo.setReadOnly(true);
    	} else {
    		costo.setReadOnly(false);
    	}
        nome.focus();
    }
}
