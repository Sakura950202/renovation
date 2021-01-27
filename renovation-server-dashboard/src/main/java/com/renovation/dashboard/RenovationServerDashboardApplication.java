package com.renovation.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringCloudApplication //相当于这三个注解@SpringBootApplication @EnableDiscoveryClient @EnableCircuitBreaker
@EnableHystrixDashboard
public class RenovationServerDashboardApplication {

    // dashboard访问地址： http://127.0.0.1:8080/hystrix

    public static void main(String[] args) {
        SpringApplication.run(RenovationServerDashboardApplication.class, args);
    }

}
