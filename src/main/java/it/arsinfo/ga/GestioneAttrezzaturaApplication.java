package it.arsinfo.ga;

import java.awt.image.BufferedImage;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.arsinfo.ga.dao.UserInfoDao;
import it.arsinfo.ga.model.entity.UserInfo;
import it.arsinfo.ga.model.entity.UserInfo.Role;

@SpringBootApplication
public class GestioneAttrezzaturaApplication {

    private static final Logger log = LoggerFactory.getLogger(GestioneAttrezzaturaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GestioneAttrezzaturaApplication.class, args);
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
    }

    @Bean
    @Transactional
    public CommandLineRunner loadData(
    		UserInfoDao userInfoDao, 
            PasswordEncoder passwordEncoder) {
    	return (args) -> {
    		UserInfo administrator = userInfoDao.findByUsername("admin");
            if (administrator == null) {
                administrator = new UserInfo("admin", passwordEncoder.encode("admin"), Role.ADMIN);
                userInfoDao.save(administrator);
                log.info("creato user admin/admin");
            }
    	};
    }
}
