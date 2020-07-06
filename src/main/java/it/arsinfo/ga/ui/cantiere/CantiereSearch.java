package it.arsinfo.ga.ui.cantiere;

import java.util.EnumSet;
import java.util.List;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.service.CantiereService;
import it.arsinfo.ga.vaadin.Search;

public class CantiereSearch extends Search<Cantiere> {

    private String searchIdentificativo;
    private StatoCantiere searchStatoCantiere;

    private final ComboBox<StatoCantiere> filterStatoCantiere = new ComboBox<StatoCantiere>("Cerca per Stato",
            EnumSet.allOf(StatoCantiere.class));
    
    private final CantiereService dao;

    public CantiereSearch(CantiereService dao) {
        super(dao);
        this.dao=dao;
        TextField filterIdentificativo = new TextField("Cerca per Identificativo");

        setComponents(
                      new HorizontalLayout(
                                           filterIdentificativo,
                                           filterStatoCantiere 
                                           ));


        filterStatoCantiere.setEmptySelectionAllowed(true);
        filterStatoCantiere.setPlaceholder("Seleziona Stato");

        filterStatoCantiere.addSelectionListener(e -> {
            if (e.getValue() == null) {
                searchStatoCantiere = null;
            } else {
                searchStatoCantiere = e.getSelectedItem().get();
            }
            onChange();
        });

        filterIdentificativo.setPlaceholder("Inserisci Nome");
        filterIdentificativo.setValueChangeMode(ValueChangeMode.EAGER);
        filterIdentificativo.addValueChangeListener(e -> {
            searchIdentificativo = e.getValue();
            onChange();
        });


    }

    @Override
    public List<Cantiere> find() {
        return dao.searchBy(searchIdentificativo,searchStatoCantiere);
    }

}
