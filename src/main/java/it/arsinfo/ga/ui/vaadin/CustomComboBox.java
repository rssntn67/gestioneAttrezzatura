package it.arsinfo.ga.ui.vaadin;

import java.util.Collection;

import com.vaadin.ui.ComboBox;

public class CustomComboBox<T> extends UIChangeHandler {

    private final ComboBox<T> tComboBox;
    private T object;

    public CustomComboBox(String placeholder, Collection<T> items) {
        tComboBox = new ComboBox<>();
        tComboBox.setPlaceholder(placeholder);
        tComboBox.setItems(items);
        
        setChangeHandler(()->{});
        tComboBox.addValueChangeListener(e -> {
            object = e.getValue();
            onChange();
        });

        setComponents(tComboBox);

    }
    
    public ComboBox<T> getComboBox() {
        return tComboBox;
    }
    
    public T getValue() {
        return object;
    }

}
