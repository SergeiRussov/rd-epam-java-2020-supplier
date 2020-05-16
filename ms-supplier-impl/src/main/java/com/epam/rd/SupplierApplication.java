package com.epam.rd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class SupplierApplication {
    public static void main(String[] args) {
        log.info("Starting configuration of Spring context");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd");
        context.getEnvironment().setActiveProfiles("test");

        log.info("Hello world!");
    }
}
