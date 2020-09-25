package it.arsinfo.ga.ui.operazione.attrezzatura;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;

import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.service.OperazioneService;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneAdd;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneEditor;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneSearch;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneUI;

@SpringUI(path = OperazioneUI.URL_OPERAZIONE_ATTREZZATURA)
@Title(OperazioneUI.TITLE_OPERAZIONE_ATTREZZATURA)
public class OperazioneAttrezzaturaUI extends OperazioneUI<Attrezzatura, OperazioneAttrezzatura> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5114046312816971846L;

	private final class OperazioneAttrezzaturaEditor extends OperazioneEditor<Attrezzatura,OperazioneAttrezzatura> {
		
		public OperazioneAttrezzaturaEditor(OperazioneService<Attrezzatura, OperazioneAttrezzatura> service) {
			super(service, new Binder<>(OperazioneAttrezzatura.class));
			setComponents(getActions(),new HorizontalLayout(getCantiereBox(),getOperabileBox(),getOperatoreBox(),getTipoBox()));
		}

		@Override
		public void focus() {
		}
	}
	@Autowired
	private OperazioneService<Attrezzatura, OperazioneAttrezzatura> service;
	
	@Override
	protected void init(VaadinRequest request) {
		OperazioneAdd<OperazioneAttrezzatura> add = 
					new OperazioneAdd<OperazioneAttrezzatura>("Aggiungi") {

			@Override
			public OperazioneAttrezzatura generate() {
				return new OperazioneAttrezzatura();
			}
		};
				
		OperazioneAttrezzaturaEditor editor = new OperazioneAttrezzaturaEditor(service);
		
		OperazioneSearch<Attrezzatura,OperazioneAttrezzatura> search = new OperazioneSearch<Attrezzatura,OperazioneAttrezzatura>(service) {
		};
		OperazioneAttrezzaturaGrid grid = new OperazioneAttrezzaturaGrid("Operazioni/Attrezzatura");
		super.init(request,add,search,editor,grid,"Operazione Attrezzatura");

		grid.populate(service.findAll());

	}

	
}
