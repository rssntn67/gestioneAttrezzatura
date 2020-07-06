package it.arsinfo.ga.ui.attrezzatura;

import java.util.EnumSet;
import java.util.List;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.service.AttrezzaturaService;
import it.arsinfo.ga.ui.vaadin.Search;

public class AttrezzaturaSearch extends Search<Attrezzatura> {

    private String searchIdentificativo;
    private StatoOperabile searchStatoAttrezzatura;
    private ModelloAttrezzatura searchModelloAttrezzatura;

    private final ComboBox<StatoOperabile> filterStatoAttrezzatura = new ComboBox<StatoOperabile>("Cerca per Stato",
            EnumSet.allOf(StatoOperabile.class));

    private final ComboBox<ModelloAttrezzatura> filterModelloAttrezzatura = new ComboBox<ModelloAttrezzatura>("Cerca per Modello");
    
    private final AttrezzaturaService dao;

    public AttrezzaturaSearch(AttrezzaturaService dao) {
        super(dao);
        this.dao=dao;
        TextField filterIdentificativo = new TextField("Cerca per Identificativo");

        setComponents(
                      new HorizontalLayout(
                                           filterIdentificativo,
                                           filterStatoAttrezzatura, 
                                           filterModelloAttrezzatura
                                           ));


        filterStatoAttrezzatura.setEmptySelectionAllowed(true);
        filterStatoAttrezzatura.setPlaceholder("Seleziona Stato");

        filterModelloAttrezzatura.setEmptySelectionAllowed(true);
        filterModelloAttrezzatura.setPlaceholder("Seleziona Modello");
        filterModelloAttrezzatura.setItems(dao.getModelli());

        filterStatoAttrezzatura.addSelectionListener(e -> {
            if (e.getValue() == null) {
                searchStatoAttrezzatura = null;
            } else {
                searchStatoAttrezzatura = e.getSelectedItem().get();
            }
            onChange();
        });

        filterModelloAttrezzatura.addSelectionListener(e -> {
            if (e.getValue() == null) {
            	searchModelloAttrezzatura = null;
            } else {
            	searchModelloAttrezzatura = e.getSelectedItem().get();
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
    public List<Attrezzatura> find() {
        return dao.searchBy(searchStatoAttrezzatura, searchIdentificativo, searchModelloAttrezzatura);
    }

}
