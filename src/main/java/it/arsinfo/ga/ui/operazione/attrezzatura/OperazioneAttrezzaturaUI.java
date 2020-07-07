package it.arsinfo.ga.ui.operazione.attrezzatura;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Title;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;

import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.service.OperazioneService;
import it.arsinfo.ga.ui.vaadin.operazione.Add;
import it.arsinfo.ga.ui.vaadin.operazione.Editor;
import it.arsinfo.ga.ui.vaadin.operazione.OperazioneUI;

@SpringUI(path = OperazioneUI.URL_OPERAZIONE_ATTREZZATURA)
@Title(OperazioneUI.TITLE_OPERAZIONE_ATTREZZATURA)
public class OperazioneAttrezzaturaUI extends OperazioneUI<ModelloAttrezzatura, Attrezzatura, OperazioneAttrezzatura> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5114046312816971846L;

	private final class OperazioneAttrezzaturaEditor extends Editor<ModelloAttrezzatura,Attrezzatura,OperazioneAttrezzatura> {
		
		public OperazioneAttrezzaturaEditor(OperazioneService<ModelloAttrezzatura, Attrezzatura, OperazioneAttrezzatura> service) {
			super(service, new Binder<>(OperazioneAttrezzatura.class));
			setComponents(getActions(),new HorizontalLayout(getCantiere(),getOperabile(),getTipo()));
		}

		@Override
		public void focus() {
		}
	}
	@Autowired
	private OperazioneService<ModelloAttrezzatura, Attrezzatura, OperazioneAttrezzatura> service;
	
	@Override
	protected void init(VaadinRequest request) {
		Add<ModelloAttrezzatura,Attrezzatura,OperazioneAttrezzatura> add = 
					new Add<ModelloAttrezzatura,Attrezzatura,OperazioneAttrezzatura>("Aggiungi") {

			@Override
			public OperazioneAttrezzatura generate() {
				return new OperazioneAttrezzatura();
			}
		};
		
		OperazioneAttrezzaturaEditor editor = new OperazioneAttrezzaturaEditor(service);
		
		super.init(request,add,editor,"Operazione Attrezzatura");
		addComponents(add,editor);

        editor.setVisible(false);

	}

	
}
