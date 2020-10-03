package it.arsinfo.ga.ui.security;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.arsinfo.ga.model.entity.UserInfo;
import it.arsinfo.ga.service.UserInfoService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserInfoService service;

    private static final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	UserInfo user = service.findByUsername(username);
        if (null == user) {
        	log.info("login: '{}' not found, access is denied.", username);
            throw new UsernameNotFoundException("No user found with username: "
                + username);
        }
        log.info("login: {}",user);        
        return new User(user.getUsername(),
        		user.getPasswordHash(),
        		Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }
    
}