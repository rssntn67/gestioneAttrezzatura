package it.arsinfo.ga.ui.operatore;

import java.util.EnumSet;
import java.util.List;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.StatoOperatore;
import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.service.OperatoreService;
import it.arsinfo.ga.ui.vaadin.entity.Search;

public class OperatoreSearch extends Search<Operatore> {

    private String searchIdentificativo;
    private StatoOperatore searchStato;

    private final ComboBox<StatoOperatore> filterStato = new ComboBox<StatoOperatore>("Cerca per Stato",
            EnumSet.allOf(StatoOperatore.class));

    private final OperatoreService dao;

    public OperatoreSearch(OperatoreService dao) {
        super(dao);
        this.dao=dao;
        TextField filterIdentificativo = new TextField("Cerca per Identificativo");

        setComponents(
                      new HorizontalLayout(
                                           filterIdentificativo,
                                           filterStato
                                           ));


        filterStato.setEmptySelectionAllowed(true);
        filterStato.setPlaceholder("Seleziona Stato");

        filterStato.addSelectionListener(e -> {
            if (e.getValue() == null) {
                searchStato = null;
            } else {
                searchStato = e.getSelectedItem().get();
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
    public List<Operatore> find() {
        return dao.searchBy(searchIdentificativo,searchStato);
    }

}
