package com.renovation.epiphyllum;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
@EnableApolloConfig
public class RenovationServerEpiphyllumApplication {

    public static void main(String[] args) {
        SpringApplication.run(RenovationServerEpiphyllumApplication.class, args);
    }

}
