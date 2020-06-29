package it.arsinfo.ga;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestioneAttrezzaturaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestioneAttrezzaturaApplication.class, args);
    }

    @Bean
    @Transactional
    public CommandLineRunner loadData() {
        return (args) -> {

        };
    }

}
