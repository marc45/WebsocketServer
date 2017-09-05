package com.shabab.simplws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;


import java.net.UnknownHostException;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static Class applicationClass = Application.class;

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(Application.class, args);


    }

    }