package com.renovation.gardenia.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MyBatisPlusConfig
 * @Description: TODO MybatisPlus配置类
 * @Author: SAKURA
 * @Date: 2020/5/4 17:47
 * @Version 1.0
 **/
@Configuration
@MapperScan(basePackages = "com.renovation.gardenia.module.**.mapper")
public class MyBatisPlusConfig {

    /**
     * 分页插件
     * 如果不配置，想要分页的sql后面是不会有limit的
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor MybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //分页配置
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

}
