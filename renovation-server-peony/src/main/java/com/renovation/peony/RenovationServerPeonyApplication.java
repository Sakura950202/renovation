package com.renovation.peony;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
@EnableApolloConfig
public class RenovationServerPeonyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RenovationServerPeonyApplication.class, args);
    }

}
