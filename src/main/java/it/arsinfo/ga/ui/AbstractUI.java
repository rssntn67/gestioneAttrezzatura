    package it.arsinfo.ga.ui;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.MenuItem;

import it.arsinfo.ga.ui.vaadin.UIChangeHandler;

@Push
public abstract class AbstractUI extends UI {

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
    public final static String URL_MODELLO_ATTREZZATURA = "/modello/attrezzatura";
    public final static String URL_ATTREZZATURA = "/attrezzatura";
    public final static String URL_MODELLO_CONSUMABILE = "/modello/consumabile";
    public final static String URL_CONSUMABILE = "/consumabile";
    public final static String URL_MODELLO_PERSONALE = "/modello/personale";
    public final static String URL_PERSONALE = "/personale";
    public final static String URL_CANTIERE = "/cantiere";


    public static final String TITLE_HOME = "Home";
    public final static String TITLE_MODELLO_ATTREZZATURA = "Modello Attrezzatura";
    public final static String TITLE_MODELLO_CONSUMABILE = "Modello Consumabile";
    public final static String TITLE_MODELLO_PERSONALE = "Modello Personale";
    public final static String TITLE_ATTREZZATURA = "Attrezzatura";
    public final static String TITLE_CONSUMABILE = "Consumabile";
    public final static String TITLE_PERSONALE = "Personale";
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

        MenuItem modelli = menu.addItem("Gestione Modelli",null);
        modelli.addItem(TITLE_MODELLO_ATTREZZATURA,new MenuBar.Command() {
            private static final long serialVersionUID = 1L;
            
            public void menuSelected(MenuItem selectedItem) {
                getUI().getPage().setLocation(URL_MODELLO_ATTREZZATURA);
            }
        });

        modelli.addItem(TITLE_MODELLO_CONSUMABILE,new MenuBar.Command() {
            private static final long serialVersionUID = 1L;
            
            public void menuSelected(MenuItem selectedItem) {
                getUI().getPage().setLocation(URL_MODELLO_CONSUMABILE);
            }
        });

        modelli.addItem(TITLE_MODELLO_PERSONALE,new MenuBar.Command() {
            private static final long serialVersionUID = 1L;
            
            public void menuSelected(MenuItem selectedItem) {
                getUI().getPage().setLocation(URL_MODELLO_PERSONALE);
            }
        });

        MenuItem operabili = menu.addItem("Gestione Operabili",null);
        operabili.addItem(TITLE_ATTREZZATURA,new MenuBar.Command() {
            private static final long serialVersionUID = 1L;
            
            public void menuSelected(MenuItem selectedItem) {
                getUI().getPage().setLocation(URL_ATTREZZATURA);
            }
        });

        operabili.addItem(TITLE_CONSUMABILE,new MenuBar.Command() {
            private static final long serialVersionUID = 1L;
            
            public void menuSelected(MenuItem selectedItem) {
                getUI().getPage().setLocation(URL_CONSUMABILE);
            }
        });

        operabili.addItem(TITLE_PERSONALE,new MenuBar.Command() {
            private static final long serialVersionUID = 1L;
            
            public void menuSelected(MenuItem selectedItem) {
                getUI().getPage().setLocation(URL_PERSONALE);
            }
        });

        MenuItem cantiere = menu.addItem("Gestione Cantiere",null);
        cantiere.addItem(TITLE_CANTIERE,new MenuBar.Command() {
            private static final long serialVersionUID = 1L;
            
            public void menuSelected(MenuItem selectedItem) {
                getUI().getPage().setLocation(URL_CANTIERE);
            }
        });

        cantiere.addItem("Operazioni Cantiere",null);


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
