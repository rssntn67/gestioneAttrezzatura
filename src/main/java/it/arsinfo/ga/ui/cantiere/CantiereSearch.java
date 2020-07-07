package it.arsinfo.ga.ui.cantiere;

import java.util.EnumSet;
import java.util.List;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.TipoCantiere;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.service.CantiereService;
import it.arsinfo.ga.ui.vaadin.entity.Search;

public class CantiereSearch extends Search<Cantiere> {

    private String searchIdentificativo;
    private StatoCantiere searchStato;
    private TipoCantiere searchTipo;

    private final ComboBox<StatoCantiere> filterStato = new ComboBox<StatoCantiere>("Cerca per Stato",
            EnumSet.allOf(StatoCantiere.class));

    private final ComboBox<TipoCantiere> filterTipo = new ComboBox<TipoCantiere>("Cerca per Tipo",
            EnumSet.allOf(TipoCantiere.class));

    private final CantiereService dao;

    public CantiereSearch(CantiereService dao) {
        super(dao);
        this.dao=dao;
        TextField filterIdentificativo = new TextField("Cerca per Identificativo");

        setComponents(
                      new HorizontalLayout(
                                           filterIdentificativo,
                                           filterStato,
                                           filterTipo
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

        filterTipo.setEmptySelectionAllowed(true);
        filterTipo.setPlaceholder("Seleziona Tipo");

        filterTipo.addSelectionListener(e -> {
            if (e.getValue() == null) {
                searchTipo = null;
            } else {
                searchTipo = e.getSelectedItem().get();
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
        return dao.searchBy(searchIdentificativo,searchStato,searchTipo);
    }

}
