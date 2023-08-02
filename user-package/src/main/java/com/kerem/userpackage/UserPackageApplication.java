package com.kerem.userpackage;

import com.kerem.commonpackage.utils.constants.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage, Paths.User.ServiceBasePackage})
public class UserPackageApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserPackageApplication.class, args);
    }

}
