package it.arsinfo.ga.service;

import java.util.List;

import it.arsinfo.ga.model.entity.UserInfo;
import it.arsinfo.ga.model.entity.UserInfo.Role;

public interface UserInfoService extends EntityBaseService<UserInfo> {

	List<UserInfo> searchBy(String searchText, Role role);
	UserInfo findByUsername(String name);	
}
