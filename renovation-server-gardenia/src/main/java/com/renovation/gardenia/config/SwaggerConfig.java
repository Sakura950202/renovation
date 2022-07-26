package com.renovation.gardenia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

/**
 * @ClassName SwaggerConfig
 * @Description TODO swagger配置类
 * @Author SAKURA
 * @Date 2022/2/23 16:42
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    private boolean enable = true;

    @Bean
    public Docket webApiConfig() {

        return new Docket(DocumentationType.OAS_30)
                .groupName("gardenia")
                .apiInfo(apiInfo())
                .globalRequestParameters(globalRequestParameters())
                .enable(enable)
                .select()
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Gardenia 项目前后端接口文档")
                .description("此文档专门用来规范开发中的前后端对接问题")
                .version("1.0")
                .build();
    }


    private List<RequestParameter> globalRequestParameters() {
        List<RequestParameter> requestParameters = new ArrayList<>();
        RequestParameterBuilder builder = new RequestParameterBuilder();
        requestParameters.add(
                builder.name("Authorization")
                        .required(false)
                        .in(ParameterType.HEADER)
                        .build()
        );
        return requestParameters;
    }

}
