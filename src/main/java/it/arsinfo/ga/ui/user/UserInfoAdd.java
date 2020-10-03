package it.arsinfo.ga.ui.user;

import it.arsinfo.ga.model.entity.UserInfo;
import it.arsinfo.ga.ui.vaadin.entity.Add;

public class UserInfoAdd extends Add<UserInfo> {

    public UserInfoAdd(String caption) {
        super(caption);
    }
    
    @Override
    public UserInfo generate() {
        return new UserInfo();
    }

}
