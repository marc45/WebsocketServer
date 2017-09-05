package com.shabab.simplws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static Class applicationClass = Application.class;

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(Application.class, args);

        testIp();
    }
    private static void testIp() throws UnknownHostException {

        long ipAddress = new BigInteger(InetAddress.getByName("176.9.251.198").getAddress()).longValue();

        System.out.println("By String IP address: \n" +
                GeoIPv4.getLocation("176.9.251.198"));

        System.out.println("By long IP address: \n" +
                GeoIPv4.getLocation(ipAddress));

        System.out.println("By InetAddress IP address: \n" +
                GeoIPv4.getLocation(InetAddress.getByName("176.9.251.198")));

    }
    }