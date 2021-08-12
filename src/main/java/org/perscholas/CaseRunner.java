package org.perscholas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaseRunner {
    final static Logger log = LoggerFactory.getLogger(CaseRunner.class);
    public static void main(String[] args) {
        SpringApplication.run(CaseRunner.class, args);
        //log.trace("Can you see me?");
    }
}
