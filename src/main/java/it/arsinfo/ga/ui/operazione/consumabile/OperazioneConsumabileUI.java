package it.arsinfo.ga.ui.operazione.consumabile;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.ModelloConsumabile;
import it.arsinfo.ga.model.entity.OperazioneConsumabile;
import it.arsinfo.ga.service.OperazioneService;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneAdd;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneEditor;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneSearch;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneUI;

@SpringUI(path = OperazioneUI.URL_OPERAZIONE_CONSUMABILE)
@Title(OperazioneUI.TITLE_OPERAZIONE_CONSUMABILE)
public class OperazioneConsumabileUI extends OperazioneUI<ModelloConsumabile, Consumabile, OperazioneConsumabile> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5114046312816971846L;

	private final class OperazioneConsumabileEditor extends OperazioneEditor<ModelloConsumabile,Consumabile,OperazioneConsumabile> {

	    private final TextField numero = new TextField("Qu.ta");

		public OperazioneConsumabileEditor(OperazioneService<ModelloConsumabile, Consumabile, OperazioneConsumabile> service) {
			super(service, new Binder<>(OperazioneConsumabile.class));
			setComponents(getActions(),new HorizontalLayout(getCantiere(),getOperabile(),getTipo(),numero));
			
	        getBinder()
	        .forField(numero)
	        .withConverter(new StringToIntegerConverter("Deve essere un numero"))
	        .withValidator(num -> num != null && num > 0,"deve essere maggiore di 0")
	        .bind(OperazioneConsumabile::getNumero, OperazioneConsumabile::setNumero);

		}

		@Override
		public void focus() {
		}
	}
	@Autowired
	private OperazioneService<ModelloConsumabile, Consumabile, OperazioneConsumabile> service;
	
	@Override
	protected void init(VaadinRequest request) {
		OperazioneAdd<ModelloConsumabile,Consumabile,OperazioneConsumabile> add = 
					new OperazioneAdd<ModelloConsumabile,Consumabile,OperazioneConsumabile>("Aggiungi") {

			@Override
			public OperazioneConsumabile generate() {
				return new OperazioneConsumabile();
			}
		};
		
		OperazioneConsumabileEditor editor = new OperazioneConsumabileEditor(service);
		
		OperazioneSearch<ModelloConsumabile,Consumabile,OperazioneConsumabile> search = new OperazioneSearch<ModelloConsumabile,Consumabile,OperazioneConsumabile>(service) {
		};
		OperazioneConsumabileGrid grid = new OperazioneConsumabileGrid("Operazioni/Consumabile");
		super.init(request,add,search,editor,grid,"Operazione Consumabile");
		addComponents(add,search,grid,editor);

        editor.setVisible(false);
        grid.populate(service.findAll());

	}

	
}
