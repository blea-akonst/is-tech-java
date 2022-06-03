package com.akonst.kotikijava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.akonst.kotikijava.dao.repositories")
@EntityScan("com.akonst.kotikijava.dao.models")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class KotikiJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KotikiJavaApplication.class, args);
    }

}
