
package it.arsinfo.ga.ui.vaadin.operazione;

import java.util.EnumSet;
import java.util.List;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;

import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Modello;
import it.arsinfo.ga.model.entity.Operabile;
import it.arsinfo.ga.model.entity.Operazione;
import it.arsinfo.ga.service.OperazioneService;
import it.arsinfo.ga.ui.vaadin.UIChangeHandler;

public abstract class OperazioneSearch<K extends Modello,T extends Operabile<K>, S extends Operazione<K, T>>
        extends UIChangeHandler {

    private final OperazioneService<K,T,S> service;
    private final ComboBox<TipoOperazione> ftipo = new ComboBox<TipoOperazione>("Tipo Operazione",EnumSet.allOf(TipoOperazione.class));

    private final ComboBox<Cantiere> fcantiere = new ComboBox<Cantiere>("Cantiere");
    private final ComboBox<T> foperabile = new ComboBox<T>("Operabile");
        
    public OperazioneSearch(OperazioneService<K,T,S> service) {
        this.service=service;
        
        fcantiere.setItemCaptionGenerator(Cantiere::getHeader);
        fcantiere.setEmptySelectionAllowed(false);
        fcantiere.setItems(service.getCantieri());
        
        foperabile.setItemCaptionGenerator(Operabile::getHeader);
        foperabile.setEmptySelectionAllowed(false);
        foperabile.setItems(service.getOperabili());

        HorizontalLayout searchbar = new HorizontalLayout(ftipo);
        searchbar.addComponentsAndExpand(foperabile,fcantiere);
        setComponents(searchbar);
        
        ftipo.setEmptySelectionAllowed(true);
        ftipo.setPlaceholder("Seleziona Tipo Operazione");
        ftipo.addSelectionListener(e -> {
             onChange();
         });
        
        foperabile.setEmptySelectionAllowed(true);
        foperabile.setPlaceholder("Seleziona Operabile");
        foperabile.addSelectionListener(e -> {
             onChange();
         });
        
        fcantiere.setEmptySelectionAllowed(true);
        fcantiere.setPlaceholder("Seleziona Cantiere");
        fcantiere.addSelectionListener(e -> {
             onChange();
         });


    }

    public List<S> find() {
    	return service.searchBy(fcantiere.getValue(), foperabile.getValue(), ftipo.getValue());
    }
        
    public List<S> findAll() {
        return service.findAll();
    }

}
