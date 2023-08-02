package com.kerem.packetservicee;

import com.kerem.commonpackage.utils.constants.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.kerem.packetservicee", Paths.ConfigurationBasePackage})
public class PacketServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacketServiceApplication.class, args);
    }

}
