package it.arsinfo.ga.ui.modello.attrezzatura;

import java.util.EnumSet;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.MarcaAttrezzatura;
import it.arsinfo.ga.model.data.TipoAttrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.service.ModelloAttrezzaturaService;
import it.arsinfo.ga.ui.vaadin.entity.Editor;

public class ModelloAttrezzaturaEditor extends Editor<ModelloAttrezzatura> {

    private final ComboBox<Fornitore> fornitore = new ComboBox<Fornitore>("Fornitore",
            EnumSet.allOf(Fornitore.class));
    private final ComboBox<Anno> annoProduzione = new ComboBox<Anno>("Anno Produzione",
            EnumSet.allOf(Anno.class));
    private final ComboBox<TipoAttrezzatura> tipo = new ComboBox<TipoAttrezzatura>("Tipo Attrezzatura",
                                                                           EnumSet.allOf(TipoAttrezzatura.class));
    private final ComboBox<MarcaAttrezzatura> marca = new ComboBox<MarcaAttrezzatura>("Marca Attrezzatura",
                                                                                            EnumSet.allOf(MarcaAttrezzatura.class));
    private final TextField nome = new TextField("Nome");
    private final TextField descrizione = new TextField("Descrizione");
    private final TextField costo = new TextField("costo");

    public ModelloAttrezzaturaEditor(ModelloAttrezzaturaService dao) {
        super(dao, new Binder<>(ModelloAttrezzatura.class));

        HorizontalLayout intestazioni = new HorizontalLayout(nome,fornitore,tipo,marca);

        HorizontalLayout residenza = new HorizontalLayout(costo,tipo,marca);
        residenza.addComponentsAndExpand(descrizione);
        
        setComponents(getActions(), intestazioni,residenza,getImage());


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
    public void focus(boolean persisted, ModelloAttrezzatura c) {
    	if (persisted) {
    		costo.setReadOnly(true);
    	} else {
    		costo.setReadOnly(false);
    	}
        nome.focus();
    }
}
