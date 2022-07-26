package com.renovation.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName SwaggerProvider
 * @Description TODO
 * @Author SAKURA
 * @Date 2022/2/24 10:19
 */
@Slf4j
@Primary
@Component
public class SwaggerProvider implements SwaggerResourcesProvider {

    private static final String API_URI = "/v3/api-docs";

    private List<String> ignoreProviders = new ArrayList<>();

    @Autowired
    private GatewayProperties gatewayProperties;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        Set<String> dealed = new HashSet<>();// 记录已经添加过的server
        gatewayProperties.getRoutes().stream()
                .filter(routeDefinition -> !ignoreProviders.contains(routeDefinition.getUri().getHost()))
                .forEach(routeDefinition -> {
                            String url = "/" + routeDefinition.getUri().getHost().toLowerCase() + "/" + routeDefinition.getId() + API_URI;
                            if (!dealed.contains(url)) {
                                dealed.add(url);
                                SwaggerResource swaggerResource = new SwaggerResource();
                                swaggerResource.setUrl(url);
                                swaggerResource.setName(routeDefinition.getUri().getHost());
                                swaggerResource.setSwaggerVersion("3.0");
                                resources.add(swaggerResource);
                            }
                        }
                );
        return resources;
    }
}
