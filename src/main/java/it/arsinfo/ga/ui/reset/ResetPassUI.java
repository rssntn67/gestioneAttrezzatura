package it.arsinfo.ga.ui.reset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import it.arsinfo.ga.model.entity.UserInfo;
import it.arsinfo.ga.service.UserInfoService;
import it.arsinfo.ga.ui.AbstractUI;
import it.arsinfo.ga.ui.vaadin.entity.EntityBaseUI;

@SpringUI(path=AbstractUI.URL_RESET_PASS)
@Title("Reset Password")
public class ResetPassUI extends EntityBaseUI<UserInfo> {

    @Autowired
    private UserInfoService service;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 
     */
    private static final long serialVersionUID = -659806613407638574L;

    @Override
    protected void init(VaadinRequest request) {
        super.init(request,"Reset Password");
        ResetPassEditor editor = new ResetPassEditor(service,passwordEncoder);
        
        addComponents(editor);
        
        editor.setChangeHandler(() -> {
        });
        
        editor.edit(getLoggedInUser());
        
    }

}
