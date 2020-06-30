    package it.arsinfo.ga.vaadin;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.MenuItem;

@Push
public abstract class CustomUI extends UI {

    /**
     * 
     */
    private static final long serialVersionUID = 7884064928998716106L;
    private VerticalLayout layout = new VerticalLayout();
    private MenuBar menu = new MenuBar();
    private Label header = new Label("");
    public static final String HOME = "/";
    public final static String URL_LOGIN = "/login.html";
    public final static String URL_LOGIN_PROCESSING = "/login";
    public final static String URL_LOGIN_FAILURE = "/login.html?error";
    public final static String URL_LOGOUT = "/logout";
    public final static String URL_MODELLO_ATTREZZATURA = "/modelloAttrezzatura";
    public final static String URL_ATTREZZATURA = "/attrezzatura";
    public final static String URL_CANTIERE = "/cantiere";


    public static final String TITLE_HOME = "Home";
    public final static String TITLE_MODELLO_ATTREZZATURA = "Modello Attrezzatura";
    public final static String TITLE_ATTREZZATURA = "Attrezzatura";
    public final static String TITLE_CANTIERE = "Cantiere";

    protected void init(VaadinRequest request, String head) {

        header.setValue(head);
        layout.addComponent(menu);
        layout.addComponent(header);
        setContent(layout);   
        
        menu.addItem(TITLE_HOME,new MenuBar.Command() {
            private static final long serialVersionUID = 1L;
            
            public void menuSelected(MenuItem selectedItem) {
                getUI().getPage().setLocation(HOME);
            }
        });

        menu.addItem(TITLE_MODELLO_ATTREZZATURA,new MenuBar.Command() {
            private static final long serialVersionUID = 1L;
            
            public void menuSelected(MenuItem selectedItem) {
                getUI().getPage().setLocation(URL_MODELLO_ATTREZZATURA);
            }
        });
        
        menu.addItem(TITLE_ATTREZZATURA,new MenuBar.Command() {
            private static final long serialVersionUID = 1L;
            
            public void menuSelected(MenuItem selectedItem) {
                getUI().getPage().setLocation(URL_ATTREZZATURA);
            }
        });
        
        menu.addItem(TITLE_CANTIERE,new MenuBar.Command() {
            private static final long serialVersionUID = 1L;
            
            public void menuSelected(MenuItem selectedItem) {
                getUI().getPage().setLocation(URL_CANTIERE);
            }
        });



    }

    protected void setExpandRatio(Component component, float ratio) {
        layout.setExpandRatio(component, ratio);
    }

    protected void hideMenu() {
        menu.setVisible(false);
    }

    protected void showMenu() {
        menu.setVisible(true);
    }

    protected void setHeader(String head) {
        header.setValue(head);
    }

    protected void addSmdComponents(UIChangeHandler ... smdChangeHandlers) {
        for (UIChangeHandler smdChangeHandler: smdChangeHandlers) {
            layout.addComponents(smdChangeHandler.getComponents());
        }
    }
    
    protected void addComponents(Component...components) {
        layout.addComponents(components);
    }
    
}
