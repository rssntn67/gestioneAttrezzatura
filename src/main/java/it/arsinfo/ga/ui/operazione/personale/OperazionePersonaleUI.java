package it.arsinfo.ga.ui.operazione.personale;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.model.entity.OperazionePersonale;
import it.arsinfo.ga.service.OperazioneService;
import it.arsinfo.ga.ui.vaadin.operazione.Add;
import it.arsinfo.ga.ui.vaadin.operazione.Editor;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneUI;

@SpringUI(path = OperazioneUI.URL_OPERAZIONE_PERSONALE)
@Title(OperazioneUI.TITLE_OPERAZIONE_PERSONALE)
public class OperazionePersonaleUI extends OperazioneUI<ModelloPersonale, Personale, OperazionePersonale> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5114046312816971846L;

	private final class OperazionePersonaleEditor extends Editor<ModelloPersonale,Personale,OperazionePersonale> {

	    private final TextField numero = new TextField("Qu.ta");

		public OperazionePersonaleEditor(OperazioneService<ModelloPersonale, Personale, OperazionePersonale> service) {
			super(service, new Binder<>(OperazionePersonale.class));
			setComponents(getActions(),new HorizontalLayout(getCantiere(),getOperabile(),getTipo(),numero));
			
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
	private OperazioneService<ModelloPersonale, Personale, OperazionePersonale> service;
	
	@Override
	protected void init(VaadinRequest request) {
		Add<ModelloPersonale,Personale,OperazionePersonale> add = 
					new Add<ModelloPersonale,Personale,OperazionePersonale>("Aggiungi") {

			@Override
			public OperazionePersonale generate() {
				return new OperazionePersonale();
			}
		};
		
		OperazionePersonaleEditor editor = new OperazionePersonaleEditor(service);
		
		super.init(request,add,editor,"Operazione Personale");
		addComponents(add,editor);

        editor.setVisible(false);

	}

	
}
