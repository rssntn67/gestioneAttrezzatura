package it.arsinfo.ga.vaadin;

import com.vaadin.ui.ProgressBar;

public class CustomProgressBar extends UIChangeHandler {

    private final ProgressBar progressBar = new ProgressBar(0.0f);

    public CustomProgressBar() {
        setComponents(progressBar);

    }
    
    public void setIndeterminate(boolean value) {
        progressBar.setIndeterminate(value);
        onChange();
    }

    public void setValue(float value) {
        progressBar.setValue(value);
        onChange();
    }
    
    public float getValue() {
        return progressBar.getValue();
    }

}
