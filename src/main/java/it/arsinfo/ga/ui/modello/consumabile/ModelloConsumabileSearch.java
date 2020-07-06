package it.arsinfo.ga.ui.modello.consumabile;

import java.util.EnumSet;
import java.util.List;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.MarcaConsumabile;
import it.arsinfo.ga.model.data.TipoConsumabile;
import it.arsinfo.ga.model.entity.ModelloConsumabile;
import it.arsinfo.ga.service.ModelloConsumabileService;
import it.arsinfo.ga.ui.vaadin.entity.Search;

public class ModelloConsumabileSearch extends Search<ModelloConsumabile> {

    private String searchNome;
    private Fornitore searchFornitore;
    private Anno searchAnnoProduzione;
    private MarcaConsumabile searchMarca;
    private TipoConsumabile searchTipo;

    private final ComboBox<Fornitore> filterFornitore = new ComboBox<Fornitore>("Cerca per Fornitore",
            EnumSet.allOf(Fornitore.class));

    private final ComboBox<Anno> filterAnno = new ComboBox<Anno>("Cerca per Anno",
            EnumSet.allOf(Anno.class));

    private final ComboBox<MarcaConsumabile> filterMarca = new ComboBox<MarcaConsumabile>("Cerca per Marca",
            EnumSet.allOf(MarcaConsumabile.class));

    private final ComboBox<TipoConsumabile> filterTipo = new ComboBox<TipoConsumabile>("Cerca per Tipo",
            EnumSet.allOf(TipoConsumabile.class));

    
    private final ModelloConsumabileService dao;

    public ModelloConsumabileSearch(ModelloConsumabileService dao) {
        super(dao);
        this.dao=dao;
        TextField filterNome = new TextField("Cerca per Nome");

        setComponents(
                      new HorizontalLayout(
                                           filterNome,
                                           filterAnno,
                                           filterFornitore),
                      new HorizontalLayout(
                                           filterTipo,
                                           filterMarca
                                           ));


       filterFornitore.setEmptySelectionAllowed(true);
       filterFornitore.setPlaceholder("Seleziona Fornitore");
       filterFornitore.addSelectionListener(e -> {
            if (e.getValue() == null) {
                searchFornitore = null;
            } else {
                searchFornitore = e.getSelectedItem().get();
            }
            onChange();
        });

        filterAnno.setEmptySelectionAllowed(true);
        filterAnno.setItemCaptionGenerator(Anno::getAnnoAsString);
        filterAnno.setPlaceholder("Seleziona Anno Produzione");

        
        filterAnno.addSelectionListener(e -> {
            if (e.getValue() == null) {
                searchAnnoProduzione = null;
            } else {
                searchAnnoProduzione = e.getSelectedItem().get();
            }
            onChange();
        });

        filterTipo.addSelectionListener(e -> {
            if (e.getValue() == null) {
            	searchTipo = null;
            } else {
            	searchTipo = e.getSelectedItem().get();
            }
            onChange();
        });

        filterMarca.addSelectionListener(e -> {
            if (e.getValue() == null) {
            	searchMarca = null;
            } else {
            	searchMarca = e.getSelectedItem().get();
            }
            onChange();
        });

        filterNome.setPlaceholder("Inserisci Nome");
        filterNome.setValueChangeMode(ValueChangeMode.EAGER);
        filterNome.addValueChangeListener(e -> {
            searchNome = e.getValue();
            onChange();
        });


    }

    @Override
    public List<ModelloConsumabile> find() {
        return dao.searchBy(searchFornitore,searchAnnoProduzione,searchNome,searchTipo,searchMarca);
    }

}
