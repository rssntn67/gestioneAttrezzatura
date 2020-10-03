package it.arsinfo.ga.ui.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.UserInfo;
import it.arsinfo.ga.service.UserInfoService;
import it.arsinfo.ga.ui.vaadin.entity.EntityBaseUI;

@SpringUI(path=EntityBaseUI.URL_ADMIN_USER)
@Title("Amministrazione Utenti")
public class UserInfoUI extends EntityBaseUI<UserInfo> {

    @Autowired
    private UserInfoService userInfoDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 
     */
    private static final long serialVersionUID = -659806613407638574L;

    @Override
    protected void init(VaadinRequest request) {
        UserInfoSearch search = new UserInfoSearch(userInfoDao);
        UserInfoAdd add = new UserInfoAdd("Aggiungi Utente");
        UserInfoGrid grid = new UserInfoGrid("Users");
        UserInfoEditor editor = new UserInfoEditor(userInfoDao,passwordEncoder);
        
        init(request,add,search, editor, grid, "Amministrazione Utenti");        
        
        addComponents(editor, 
                add,
                search, 
                grid);

        editor.setVisible(false);
        
        grid.populate(search.findAll());

    }

}
