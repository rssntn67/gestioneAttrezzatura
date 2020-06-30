package it.arsinfo.ga.ui;

import java.util.EnumSet;

import com.vaadin.data.Binder;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.dao.ModelloAttrezzaturaServiceDao;
import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.MarcaModello;
import it.arsinfo.ga.data.TipoModello;
import it.arsinfo.ga.entity.ModelloAttrezzatura;
import it.arsinfo.ga.vaadin.EntityEditor;

public class ModelloAttrezzaturaEditor extends EntityEditor<ModelloAttrezzatura> {

    private final ComboBox<Anno> annoProduzione = new ComboBox<Anno>("Anno Produzione",
            EnumSet.allOf(Anno.class));
    private final ComboBox<TipoModello> tipoModello = new ComboBox<TipoModello>("Tipo Modello",
                                                                           EnumSet.allOf(TipoModello.class));
    private final ComboBox<MarcaModello> marcaModello = new ComboBox<MarcaModello>("Marca Modello",
                                                                                            EnumSet.allOf(MarcaModello.class));
    private final TextField nome = new TextField("Nome");
    private final TextField descrizione = new TextField("Descrizione");

    public ModelloAttrezzaturaEditor(ModelloAttrezzaturaServiceDao dao) {
        super(dao, new Binder<>(ModelloAttrezzatura.class));

        HorizontalLayout intestazioni = new HorizontalLayout(nome,annoProduzione);

        HorizontalLayout residenza = new HorizontalLayout(tipoModello,marcaModello);
        residenza.addComponentsAndExpand(descrizione);
        
        setComponents(getActions(), intestazioni,residenza);


        getBinder().forField(nome).asRequired();
        getBinder().forField(annoProduzione).asRequired();
        getBinder().bindInstanceFields(this);

        // Configure and style components
        annoProduzione.setItemCaptionGenerator(Anno::getAnnoAsString);
        annoProduzione.setEmptySelectionAllowed(false);
        
        tipoModello.setEmptySelectionAllowed(false);
        marcaModello.setEmptySelectionAllowed(false);
        

    }

    @Override
    public void focus(boolean persisted, ModelloAttrezzatura c) {
        nome.focus();
    }
}
