package com.kerem.salepackage;

import com.kerem.commonpackage.utils.constants.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Sale.ServiceBasePackage})
public class SalePackageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalePackageApplication.class, args);
    }

}
