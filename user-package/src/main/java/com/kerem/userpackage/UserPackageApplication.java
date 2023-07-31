package com.kerem.userpackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(scanBasePackages = {"com.kerem.commonpackage.configuration", "com.kerem.userpackage"})
public class UserPackageApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserPackageApplication.class, args);
    }

}
