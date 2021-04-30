package com.renovation.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringCloudApplication
@EnableHystrixDashboard
public class RenovationServerDashboardApplication {

    // dashboard访问地址： http://127.0.0.1:9999/hystrix

    public static void main(String[] args) {
        SpringApplication.run(RenovationServerDashboardApplication.class, args);
    }

}
