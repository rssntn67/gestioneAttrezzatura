package it.arsinfo.ga.ui.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import it.arsinfo.ga.service.UserInfoService;
import it.arsinfo.ga.ui.AbstractUI;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private UserInfoService service;
	
    private static final Logger log = LoggerFactory.getLogger(LogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        log.info("logout: {}",service.findByUsername(authentication.getName()));
        response.setStatus(HttpStatus.OK.value());
        response.sendRedirect(request.getContextPath() + AbstractUI.URL_LOGIN);
    }

}
