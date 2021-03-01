package com.renovation.gardenia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient // 开启服务注册发现
@EnableFeignClients // 开启feign客户端，用来实现远程联调
public class RenovationServerGardeniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenovationServerGardeniaApplication.class, args);
	}

}
