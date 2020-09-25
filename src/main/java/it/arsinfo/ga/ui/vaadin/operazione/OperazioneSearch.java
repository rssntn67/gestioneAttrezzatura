
package it.arsinfo.ga.ui.vaadin.operazione;

import java.util.EnumSet;
import java.util.List;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;

import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Operabile;
import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.model.entity.Operazione;
import it.arsinfo.ga.service.OperazioneService;
import it.arsinfo.ga.ui.vaadin.UIChangeHandler;

public abstract class OperazioneSearch<T extends Operabile<?>, S extends Operazione<T>>
        extends UIChangeHandler {

    private final OperazioneService<T,S> service;
    private final ComboBox<TipoOperazione> ftipo = new ComboBox<TipoOperazione>("Tipo Operazione",EnumSet.allOf(TipoOperazione.class));

    private final ComboBox<Cantiere> fcantiere = new ComboBox<Cantiere>("Cantiere");
    private final ComboBox<T> foperabile = new ComboBox<T>("Operabile");
    private final ComboBox<Operatore> foperatore = new ComboBox<Operatore>("Operabile");
        
    public OperazioneSearch(OperazioneService<T,S> service) {
        this.service=service;
        
        fcantiere.setItemCaptionGenerator(Cantiere::getHeader);
        fcantiere.setItems(service.getCantieri());
        ftipo.setEmptySelectionAllowed(true);
        ftipo.setPlaceholder("Seleziona Tipo Operazione");
        ftipo.addSelectionListener(e -> {
             onChange();
         });
        
        foperabile.setItemCaptionGenerator(Operabile::getHeader);
        foperabile.setItems(service.getOperabili());
        foperabile.setEmptySelectionAllowed(true);
        foperabile.setPlaceholder("Seleziona Operabile");
        foperabile.addSelectionListener(e -> {
             onChange();
         });        

        foperatore.setItemCaptionGenerator(Operatore::getHeader);
        foperatore.setItems(service.getOperatori());
        fcantiere.setEmptySelectionAllowed(true);
        fcantiere.setPlaceholder("Seleziona Cantiere");
        fcantiere.addSelectionListener(e -> {
             onChange();
         });

        HorizontalLayout searchbar = new HorizontalLayout(ftipo);
        searchbar.addComponentsAndExpand(foperabile,fcantiere,foperatore);
        setComponents(searchbar);
        


    }

    public List<S> find() {
    	return service.searchBy(fcantiere.getValue(), foperabile.getValue(), ftipo.getValue(), foperatore.getValue());
    }
        
    public List<S> findAll() {
        return service.findAll();
    }

}
