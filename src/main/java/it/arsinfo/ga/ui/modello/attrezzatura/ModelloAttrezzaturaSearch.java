package it.arsinfo.ga.ui.modello.attrezzatura;

import java.util.EnumSet;
import java.util.List;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.dao.ModelloAttrezzaturaServiceDao;
import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.MarcaModello;
import it.arsinfo.ga.data.TipoModello;
import it.arsinfo.ga.entity.ModelloAttrezzatura;
import it.arsinfo.ga.vaadin.Search;

public class ModelloAttrezzaturaSearch extends Search<ModelloAttrezzatura> {

    private String searchNome;
    private Anno searchAnnoProduzione;
    private MarcaModello searchMarcaModello;
    private TipoModello searchTipoModello;

    private final ComboBox<Anno> filterAnno = new ComboBox<Anno>("Cerca per Anno",
            EnumSet.allOf(Anno.class));

    private final ComboBox<MarcaModello> filterMarca = new ComboBox<MarcaModello>("Cerca per Marca",
            EnumSet.allOf(MarcaModello.class));

    private final ComboBox<TipoModello> filterTipo = new ComboBox<TipoModello>("Cerca per Tipo",
            EnumSet.allOf(TipoModello.class));

    
    private final ModelloAttrezzaturaServiceDao dao;

    public ModelloAttrezzaturaSearch(ModelloAttrezzaturaServiceDao dao) {
        super(dao);
        this.dao=dao;
        TextField filterNome = new TextField("Cerca per Nome");

        setComponents(
                      new HorizontalLayout(
                                           filterNome,
                                           filterAnno, 
                                           filterTipo,
                                           filterMarca
                                           ));


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
            	searchTipoModello = null;
            } else {
            	searchTipoModello = e.getSelectedItem().get();
            }
            onChange();
        });

        filterMarca.addSelectionListener(e -> {
            if (e.getValue() == null) {
            	searchMarcaModello = null;
            } else {
            	searchMarcaModello = e.getSelectedItem().get();
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
        return dao.searchBy(searchAnnoProduzione,searchNome,searchTipoModello,searchMarcaModello);
    }

}
