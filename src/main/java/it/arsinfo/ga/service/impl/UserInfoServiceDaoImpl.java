package it.arsinfo.ga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.arsinfo.ga.dao.UserInfoDao;
import it.arsinfo.ga.model.entity.UserInfo;
import it.arsinfo.ga.model.entity.UserInfo.Role;
import it.arsinfo.ga.service.UserInfoService;

@Service
public class UserInfoServiceDaoImpl implements UserInfoService {

    @Autowired
    private UserInfoDao repository;

	@Override
	public UserInfo save(UserInfo entity) throws Exception {
		return repository.save(entity);
	}

	@Override
	public void delete(UserInfo entity) throws Exception {
		repository.delete(entity);
	}

	@Override
	public UserInfo findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<UserInfo> findAll() {
		return repository.findAll();
	}

	public UserInfoDao getRepository() {
		return repository;
	}

	public List<UserInfo> searchBy(String searchText, Role role) {
        if (StringUtils.isEmpty(searchText) && role == null) {
            return findAll();
        }
        if (StringUtils.isEmpty(searchText)) {
            return repository.findByRole(role);
        }
        if (role == null ) {
            return repository.findByUsernameContainingIgnoreCase(searchText);
        }
        return repository.findByUsernameContainingIgnoreCaseAndRole(searchText, role);
	}

	@Override
	public UserInfo findByUsername(String name) {
		return repository.findByUsername(name);
	}
	
}
