package it.arsinfo.ga.ui.modello.attrezzatura;

import java.util.EnumSet;

import com.vaadin.data.Binder;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.dao.ModelloAttrezzaturaServiceDao;
import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.Fornitore;
import it.arsinfo.ga.data.MarcaAttrezzatura;
import it.arsinfo.ga.data.TipoAttrezzatura;
import it.arsinfo.ga.entity.ModelloAttrezzatura;
import it.arsinfo.ga.vaadin.EntityEditor;

public class ModelloAttrezzaturaEditor extends EntityEditor<ModelloAttrezzatura> {

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

    public ModelloAttrezzaturaEditor(ModelloAttrezzaturaServiceDao dao) {
        super(dao, new Binder<>(ModelloAttrezzatura.class));

        HorizontalLayout intestazioni = new HorizontalLayout(nome,fornitore,annoProduzione);

        HorizontalLayout residenza = new HorizontalLayout(tipo,marca);
        residenza.addComponentsAndExpand(descrizione);
        
        setComponents(getActions(), intestazioni,residenza);


        getBinder().forField(nome).asRequired();
        getBinder().forField(fornitore).asRequired();
        getBinder().forField(annoProduzione).asRequired();
        getBinder().bindInstanceFields(this);

        // Configure and style components
        annoProduzione.setItemCaptionGenerator(Anno::getAnnoAsString);
        annoProduzione.setEmptySelectionAllowed(false);
        
        tipo.setEmptySelectionAllowed(false);
        marca.setEmptySelectionAllowed(false);
        

    }

    @Override
    public void focus(boolean persisted, ModelloAttrezzatura c) {
        nome.focus();
    }
}
