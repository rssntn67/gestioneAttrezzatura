package it.arsinfo.ga.ui.operazione.personale;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.entity.OperazionePersonale;
import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.service.OperazioneService;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneAdd;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneEditor;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneSearch;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneUI;

@SpringUI(path = OperazioneUI.URL_OPERAZIONE_PERSONALE)
@Title(OperazioneUI.TITLE_OPERAZIONE_PERSONALE)
public class OperazionePersonaleUI extends OperazioneUI<Personale, OperazionePersonale> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5114046312816971846L;

	private final class OperazionePersonaleEditor extends OperazioneEditor<Personale,OperazionePersonale> {

	    private final TextField numero = new TextField("Qu.ta");

	    public OperazionePersonaleEditor(OperazioneService<Personale, OperazionePersonale> service) {
		super(service, new Binder<>(OperazionePersonale.class));
                HorizontalLayout cantieriHzL = new HorizontalLayout();
                cantieriHzL.addComponentsAndExpand(getCantiereBox());
                HorizontalLayout operabileHzL = new HorizontalLayout();
                operabileHzL.addComponentsAndExpand(getOperabileBox());
                HorizontalLayout operatoreHzL = new HorizontalLayout();
                operatoreHzL.addComponentsAndExpand(getOperatoreBox());
                setComponents(new HorizontalLayout(getTipoBox(), numero),
                          operabileHzL, cantieriHzL, operatoreHzL,
                          getActions());
		
	        getBinder()
	        .forField(numero)
	        .withConverter(new StringToIntegerConverter("Deve essere un numero"))
	        .withValidator(num -> num != null && num > 0,"deve essere maggiore di 0")
	        .bind(OperazionePersonale::getNumero, OperazionePersonale::setNumero);

		}

		@Override
		public void focus() {
		}
	}
	@Autowired
	private OperazioneService<Personale, OperazionePersonale> service;
	
	@Override
	protected void init(VaadinRequest request) {
		OperazioneAdd<OperazionePersonale> add = 
					new OperazioneAdd<OperazionePersonale>("Aggiungi") {

			@Override
			public OperazionePersonale generate() {
				return new OperazionePersonale();
			}
		};
		
		OperazionePersonaleEditor editor = new OperazionePersonaleEditor(service);
		
		OperazioneSearch<Personale,OperazionePersonale> search = new OperazioneSearch<Personale,OperazionePersonale>(service) {
		};
		OperazionePersonaleGrid grid = new OperazionePersonaleGrid("Operazioni/Personale");
		super.init(request,add,search,editor,grid,"Operazione Personale");

		grid.populate(service.findAll());

	}

	
}
