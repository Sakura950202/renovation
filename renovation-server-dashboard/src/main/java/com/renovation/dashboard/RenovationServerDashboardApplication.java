package com.renovation.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard // 开启熔断监控
public class RenovationServerDashboardApplication {

    // dashboard访问地址： http://127.0.0.1:8080/hystrix

    public static void main(String[] args) {
        SpringApplication.run(RenovationServerDashboardApplication.class, args);
    }

}
