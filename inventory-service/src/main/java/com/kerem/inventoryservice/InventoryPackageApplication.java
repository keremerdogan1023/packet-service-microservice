package com.kerem.inventoryservice;

import com.kerem.commonpackage.utils.constants.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Inventory.ServiceBasePackage})
public class InventoryPackageApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryPackageApplication.class, args);
    }

}
