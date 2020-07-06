package it.arsinfo.ga.ui.consumabile;

import java.util.EnumSet;
import java.util.List;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.ModelloConsumabile;
import it.arsinfo.ga.service.ConsumabileService;
import it.arsinfo.ga.ui.vaadin.Search;

public class ConsumabileSearch extends Search<Consumabile> {

    private String searchIdentificativo;
    private StatoOperabile searchStatoConsumabile;
    private ModelloConsumabile searchModelloConsumabile;

    private final ComboBox<StatoOperabile> filterStatoConsumabile = new ComboBox<StatoOperabile>("Cerca per Stato",
            EnumSet.allOf(StatoOperabile.class));

    private final ComboBox<ModelloConsumabile> filterModelloConsumabile = new ComboBox<ModelloConsumabile>("Cerca per Modello");
    
    private final ConsumabileService dao;

    public ConsumabileSearch(ConsumabileService dao) {
        super(dao);
        this.dao=dao;
        TextField filterIdentificativo = new TextField("Cerca per Identificativo");

        setComponents(
                      new HorizontalLayout(
                                           filterIdentificativo,
                                           filterStatoConsumabile, 
                                           filterModelloConsumabile
                                           ));


        filterStatoConsumabile.setEmptySelectionAllowed(true);
        filterStatoConsumabile.setPlaceholder("Seleziona Stato");

        filterModelloConsumabile.setEmptySelectionAllowed(true);
        filterModelloConsumabile.setPlaceholder("Seleziona Modello");
        filterModelloConsumabile.setItems(dao.getModelli());

        filterStatoConsumabile.addSelectionListener(e -> {
            if (e.getValue() == null) {
                searchStatoConsumabile = null;
            } else {
                searchStatoConsumabile = e.getSelectedItem().get();
            }
            onChange();
        });

        filterModelloConsumabile.addSelectionListener(e -> {
            if (e.getValue() == null) {
            	searchModelloConsumabile = null;
            } else {
            	searchModelloConsumabile = e.getSelectedItem().get();
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
    public List<Consumabile> find() {
        return dao.searchBy(searchStatoConsumabile, searchIdentificativo, searchModelloConsumabile);
    }

}
