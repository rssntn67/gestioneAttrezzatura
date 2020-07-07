package it.arsinfo.ga.ui.vaadin.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;

import it.arsinfo.ga.model.entity.EntityBase;
import it.arsinfo.ga.qrcode.QRGenBarcodeGenerator;
import it.arsinfo.ga.service.EntityBaseService;
import it.arsinfo.ga.ui.vaadin.UIChangeHandler;

public abstract class Editor<T extends EntityBase>
        extends UIChangeHandler {

	private HorizontalLayout actions = new HorizontalLayout();
	
	private Image image;
    public HorizontalLayout getActions() {
        return actions;
    }

    private final EntityBaseService<T> repositoryDao;
    private T smdObj;

    private Button save = new Button("Salva", VaadinIcons.CHECK);
    private Button delete = new Button("Rimuovi", VaadinIcons.TRASH);
    private Button cancel = new Button("Annulla Modifiche");
    private Button back = new Button("Indietro");

    private final Binder<T> binder;
    private static final Logger log = LoggerFactory.getLogger(Editor.class);

    public Editor(EntityBaseService<T> repositoryDao, Binder<T> binder) {

		try {
			image = new Image("No QRCode", createStreamResource(QRGenBarcodeGenerator.generateQRCodeImage("No")));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

        this.repositoryDao = repositoryDao;
        this.binder = binder;

        save.addStyleName(ValoTheme.BUTTON_PRIMARY);
        delete.addStyleName(ValoTheme.BUTTON_DANGER);

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> edit(smdObj));
        back.addClickListener(e -> onChange());

        actions.addComponent(save);
        actions.addComponent(delete);
        actions.addComponent(cancel);
        actions.addComponent(back);
        
        image.setVisible(false);
    }

    public abstract void focus(boolean persisted, T obj);

    public void delete() {
        try {
            repositoryDao.delete(smdObj);
            onChange();
        } catch (Exception e) {
            Notification.show(e.getMessage(),
                              Notification.Type.ERROR_MESSAGE);
            log.error("delete: {}", e.getMessage(),e);
        }
    }

    public void save() {
        try {
            repositoryDao.save(smdObj);
            onChange();
        } catch (Exception e) {
            Notification.show(e.getMessage(),
                              Notification.Type.ERROR_MESSAGE);
            log.error("save: {}", e.getMessage(),e);
        }
    }

    public void edit(T c) {
        if (c == null) {
            setVisible(false);
            image = null;
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            smdObj = repositoryDao.findById(c.getId());
            try {
				image = new Image("QRCode", createStreamResource(QRGenBarcodeGenerator.generateQRCodeImage(smdObj.getQRCode())));
			} catch (Exception e) {
			}
            image.setVisible(true);
        } else {
            smdObj = c;
            image.setVisible(false);
       }
        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(smdObj);

        cancel.setEnabled(persisted);
        delete.setEnabled(persisted);
        focus(persisted, smdObj);
        setVisible(true);
    }

    public Button getSave() {
        return save;
    }

    public Button getCancel() {
        return cancel;
    }

    public Button getDelete() {
        return delete;
    }

    public Button getBack() {
        return back;
    }

    public Binder<T> getBinder() {
        return binder;
    }

    public T get() {
        return smdObj;
    }

    public EntityBaseService<T> getServiceDao() {
        return repositoryDao;
    }

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
