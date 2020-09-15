package it.arsinfo.ga.ui.vaadin.operazione;

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;

import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Modello;
import it.arsinfo.ga.model.entity.Operabile;
import it.arsinfo.ga.model.entity.Operazione;
import it.arsinfo.ga.service.OperazioneService;
import it.arsinfo.ga.ui.vaadin.UIChangeHandler;

public abstract class OperazioneEditor<K extends Modello,T extends Operabile<K>, S extends Operazione<K, T>>  extends UIChangeHandler {

	private HorizontalLayout actions = new HorizontalLayout();
	
    private final OperazioneService<K,T,S> service;
    
    private final ComboBox<TipoOperazione> tipo = new ComboBox<TipoOperazione>("Tipo Operazione",EnumSet.allOf(TipoOperazione.class));

    private final ComboBox<Cantiere> cantiere = new ComboBox<Cantiere>("Cantiere");
    private final ComboBox<T> operabile = new ComboBox<T>("Operabile");
    private Button esegui = new Button("Esegui Operazione", VaadinIcons.CHECK);
    private Button back = new Button("Indietro");

    private S operazione;
    private final Binder<S> binder;


    private static final Logger log = LoggerFactory.getLogger(OperazioneEditor.class);

    public OperazioneEditor(OperazioneService<K,T,S> service, Binder<S> binder) {

        this.service = service;
        this.binder=binder;

        esegui.addStyleName(ValoTheme.BUTTON_PRIMARY);

        esegui.addClickListener(e -> esegui());
        back.addClickListener(e -> onChange());

        actions.addComponent(esegui);
        actions.addComponent(back);
        
        binder.forField(tipo).asRequired().bind(Operazione<K, T>::getTipoOperazione,Operazione<K, T>::setTipoOperazione);
        binder.forField(cantiere).asRequired().bind(Operazione<K, T>::getCantiere,Operazione<K, T>::setCantiere);
        binder.forField(operabile).asRequired().bind(Operazione<K, T>::getOperabile,Operazione<K, T>::setOperabile);
        
        tipo.setEmptySelectionAllowed(false);
        
        cantiere.setItemCaptionGenerator(Cantiere::getHeader);
        cantiere.setEmptySelectionAllowed(false);
        cantiere.setItems(service.getCantieri());
        
        operabile.setItemCaptionGenerator(Operabile::getHeader);
        operabile.setEmptySelectionAllowed(false);
        operabile.setItems(service.getOperabili());
        

   }

    public void esegui() {
        try {
            service.esegui(operazione);
            onChange();
        } catch (Exception e) {
            Notification.show(e.getMessage(),
                              Notification.Type.ERROR_MESSAGE);
            log.error("save: {}", e.getMessage(),e);
        }
    }

    public S get() {
    	return operazione;
    }

    public void edit(S c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        operazione = c;
        binder.setBean(operazione);
        if (c.getId() != null) {
        	esegui.setEnabled(false);
        } else {
        	esegui.setEnabled(true);
        }

        focus();
        setVisible(true);
    }

    
    public abstract void focus();

	public HorizontalLayout getActions() {
		return actions;
	}

	public Button getEsegui() {
		return esegui;
	}


	public Button getBack() {
		return back;
	}

	public S getOperazione() {
		return operazione;
	}


	public ComboBox<TipoOperazione> getTipo() {
		return tipo;
	}

	public ComboBox<Cantiere> getCantiere() {
		return cantiere;
	}

	public ComboBox<T> getOperabile() {
		return operabile;
	}

	public Binder<S> getBinder() {
		return binder;
	}

}
