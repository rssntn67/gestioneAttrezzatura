package it.arsinfo.ga.ui.modello.attrezzatura;

import java.util.EnumSet;
import java.util.List;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.MarcaAttrezzatura;
import it.arsinfo.ga.model.data.TipoAttrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.service.ModelloAttrezzaturaService;
import it.arsinfo.ga.ui.vaadin.entity.Search;

public class ModelloAttrezzaturaSearch extends Search<ModelloAttrezzatura> {

    private String searchNome;
    private Fornitore searchFornitore;
    private Anno searchAnnoProduzione;
    private MarcaAttrezzatura searchMarca;
    private TipoAttrezzatura searchTipo;

    private final ComboBox<Fornitore> filterFornitore = new ComboBox<Fornitore>("Cerca per Fornitore",
            EnumSet.allOf(Fornitore.class));

    private final ComboBox<Anno> filterAnno = new ComboBox<Anno>("Cerca per Anno",
            EnumSet.allOf(Anno.class));

    private final ComboBox<MarcaAttrezzatura> filterMarca = new ComboBox<MarcaAttrezzatura>("Cerca per Marca",
            EnumSet.allOf(MarcaAttrezzatura.class));

    private final ComboBox<TipoAttrezzatura> filterTipo = new ComboBox<TipoAttrezzatura>("Cerca per Tipo",
            EnumSet.allOf(TipoAttrezzatura.class));

    
    private final ModelloAttrezzaturaService dao;

    public ModelloAttrezzaturaSearch(ModelloAttrezzaturaService dao) {
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
    public List<ModelloAttrezzatura> find() {
        return dao.searchBy(searchFornitore,searchAnnoProduzione,searchNome,searchTipo,searchMarca);
    }

}
