package it.arsinfo.ga.ui.personale;

import java.util.EnumSet;
import java.util.List;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.service.PersonaleService;
import it.arsinfo.ga.ui.vaadin.entity.Search;

public class PersonaleSearch extends Search<Personale> {

    private String searchIdentificativo;
    private StatoOperabile searchStatoPersonale;
    private ModelloPersonale searchModelloPersonale;

    private final ComboBox<StatoOperabile> filterStatoPersonale = new ComboBox<StatoOperabile>("Cerca per Stato",
            EnumSet.allOf(StatoOperabile.class));

    private final ComboBox<ModelloPersonale> filterModelloPersonale = new ComboBox<ModelloPersonale>("Cerca per Modello");
    
    private final PersonaleService dao;

    public PersonaleSearch(PersonaleService dao) {
        super(dao);
        this.dao=dao;
        TextField filterIdentificativo = new TextField("Cerca per Identificativo");

        setComponents(
                      new HorizontalLayout(
                                           filterIdentificativo,
                                           filterStatoPersonale, 
                                           filterModelloPersonale
                                           ));


        filterStatoPersonale.setEmptySelectionAllowed(true);
        filterStatoPersonale.setPlaceholder("Seleziona Stato");

        filterModelloPersonale.setEmptySelectionAllowed(true);
        filterModelloPersonale.setPlaceholder("Seleziona Modello");
        filterModelloPersonale.setItems(dao.getModelli());

        filterStatoPersonale.addSelectionListener(e -> {
            if (e.getValue() == null) {
                searchStatoPersonale = null;
            } else {
                searchStatoPersonale = e.getSelectedItem().get();
            }
            onChange();
        });

        filterModelloPersonale.addSelectionListener(e -> {
            if (e.getValue() == null) {
            	searchModelloPersonale = null;
            } else {
            	searchModelloPersonale = e.getSelectedItem().get();
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
    public List<Personale> find() {
        return dao.searchBy(searchStatoPersonale, searchIdentificativo, searchModelloPersonale);
    }

}
