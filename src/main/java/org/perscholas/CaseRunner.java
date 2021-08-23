package org.perscholas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories({"org.perscholas.dao","org.perscholas.test"})
//@EntityScan({"org.perscholas.dao", "org.perscholas.test"})
//@ComponentScan("org.perscholas.test")

@SpringBootApplication
public class CaseRunner {
    final static Logger log = LoggerFactory.getLogger(CaseRunner.class);
    public static void main(String[] args) {
        SpringApplication.run(CaseRunner.class, args);
        //log.trace("Can you see me?");
    }
}
