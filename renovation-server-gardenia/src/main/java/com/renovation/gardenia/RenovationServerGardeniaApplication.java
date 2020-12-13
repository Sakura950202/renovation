package com.renovation.gardenia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.renovation.gardenia.config")
public class RenovationServerGardeniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenovationServerGardeniaApplication.class, args);
	}

}
