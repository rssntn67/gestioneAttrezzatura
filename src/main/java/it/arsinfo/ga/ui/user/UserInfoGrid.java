package it.arsinfo.ga.ui.user;


import com.vaadin.ui.Grid;

import it.arsinfo.ga.model.entity.UserInfo;
import it.arsinfo.ga.ui.vaadin.entity.CustomGrid;


public class UserInfoGrid extends CustomGrid<UserInfo> {


    public UserInfoGrid(String gridname) {
        super(new Grid<>(UserInfo.class),gridname);

        setColumns("username","role","data");

    }
    
}
