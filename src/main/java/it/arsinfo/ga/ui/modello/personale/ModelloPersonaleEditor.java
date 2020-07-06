package it.arsinfo.ga.ui.modello.personale;

import java.util.EnumSet;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.dao.ModelloPersonaleServiceDao;
import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.Fornitore;
import it.arsinfo.ga.data.TipoPersonale;
import it.arsinfo.ga.entity.ModelloPersonale;
import it.arsinfo.ga.vaadin.EntityEditor;

public class ModelloPersonaleEditor extends EntityEditor<ModelloPersonale> {

    private final ComboBox<Fornitore> fornitore = new ComboBox<Fornitore>("Fornitore",
            EnumSet.allOf(Fornitore.class));
    private final ComboBox<Anno> annoProduzione = new ComboBox<Anno>("Anno Produzione",
            EnumSet.allOf(Anno.class));
    private final ComboBox<TipoPersonale> tipo = new ComboBox<TipoPersonale>("Tipo Personale",
                                                                           EnumSet.allOf(TipoPersonale.class));
    private final TextField nome = new TextField("Nome");
    private final TextField descrizione = new TextField("Descrizione");
    private final TextField costo = new TextField("costo");

    public ModelloPersonaleEditor(ModelloPersonaleServiceDao dao) {
        super(dao, new Binder<>(ModelloPersonale.class));

        HorizontalLayout intestazioni = new HorizontalLayout(nome,fornitore,annoProduzione);

        HorizontalLayout residenza = new HorizontalLayout(costo,tipo);
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
        
    }

    @Override
    public void focus(boolean persisted, ModelloPersonale c) {
    	if (persisted) {
    		costo.setReadOnly(true);
    	} else {
    		costo.setReadOnly(false);
    	}
        nome.focus();
    }
}
