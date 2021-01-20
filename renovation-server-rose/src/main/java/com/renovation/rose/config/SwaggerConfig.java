package com.renovation.rose.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Description TODO swagger接口文档配置类
 * @Author SAKURA
 * @Date 2020/12/09 17:07
 * @Version 1.0
 *
 * 目前只在dev环境和test环境使用
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Gardenia的前后端接口文档")
                .description("此文档专门用来规范开发中的前后端对接问题")
                .termsOfServiceUrl("")
                .version("1.0")
                // 作者信息
                .contact(new Contact("SAKURA", "http://127.0.0.1:8082/rose/swagger-ui.html", "email"))
                .build();
    }
}
