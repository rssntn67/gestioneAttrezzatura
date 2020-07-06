package it.arsinfo.ga.ui.vaadin;

import com.vaadin.data.Binder;

import it.arsinfo.ga.model.entity.EntityBase;

public abstract class ItemEditor<T extends EntityBase>
        extends Editor<T> {

	private T smdObj;

    private final Binder<T> binder;

    public ItemEditor(Binder<T> binder) {

        this.binder = binder;

    }

    public abstract void focus(boolean persisted, T obj);


    public final void edit(T c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        smdObj = c;
        binder.setBean(smdObj);

        focus(persisted, smdObj);
        setVisible(true);
    }

    public Binder<T> getBinder() {
        return binder;
    }

    public T get() {
        return smdObj;
    }

}
