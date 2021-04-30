package com.renovation.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MySwaggerResourceProvider
 * @Description TODO 聚合各个服务的swagger接口
 * @Author SAKURA
 * @Date 2021/4/30 9:52
 */
@Component
@Primary
@AllArgsConstructor
public class MySwaggerResourceProvider implements SwaggerResourcesProvider {

    /**
     * swagger2默认的url后缀
     */
    public static final String API_URI = "/v2/api-docs";

    /**
     * 路由定位器，用来获取网关中的路由信息
     */
    private final RouteLocator routeLocator;

    /**
     * 获取网关配置文件中，前缀为 spring.cloud.gateway 的配置信息
     */
    private final GatewayProperties gatewayProperties;

    @Override
    public List<SwaggerResource> get() {
        // 定义变量用来保存所有的swagger资源对象，让swagger可以跳转不同服务的资源
        List<SwaggerResource> resources = new ArrayList<>();
        // 定义变量用来保存所有的路由id
        List<String> routes = new ArrayList<>();

        // 获取gateway中的所有路由id
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));

        // 结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                        // 循环组装swagger资源对象
                        .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
                                predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                        .replace("/**", API_URI)))));
        return resources;
    }

    /**
     * 实例化组装swagger资源类
     *
     * @param name     资源名称（使用服务名）
     * @param location 资源地址
     * @return swagger资源类
     */
    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

}
