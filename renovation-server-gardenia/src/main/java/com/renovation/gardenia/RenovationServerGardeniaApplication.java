package com.renovation.gardenia;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
@EnableApolloConfig
@EnableSwagger2Doc
public class RenovationServerGardeniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenovationServerGardeniaApplication.class, args);
	}

}
