package com.example.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web层配置类,实现静态资源映射，将knife4j相关资源放行，保证生成的接口文档能够正常进行展示
 * @author Hva
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 设置静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 添加静态资源映射规则
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        // 配置 knife4j 的静态资源请求映射地址
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        // 添加视频资源映射
        registry.addResourceHandler("/videos/**")
                .addResourceLocations("classpath:/static/videos/");
        // 添加图片资源映射
        registry.addResourceHandler("/pic/**")
                .addResourceLocations("classpath:/static/pic/");

    }
    /*
     * 注意检查application设置：
     * spring:
     * application:
     * name: demo ***基底路径***使用所有静态资源，映射路径前缀都要加上demo，不然获取不到
     * datasource:
     * driver-class-name: com.mysql.cj.jdbc.Driver
     * url: jdbc:mysql://localhost:3306/software?serverTimezone=UTC
     * username: root
     * password: 165831
     * thymeleaf:
     * prefix: classpath:/templates/ ***模板路径***
     * suffix: .html
     * mode: HTML
     * encoding: UTF-8
     * cache: false
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 将 /user/login 映射到 login_user.html
        // 请注意视图web页面是页面，它的url不能和接口重合
        registry.addViewController("/").setViewName("welcome");
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/user_login").setViewName("login_user");
        registry.addViewController("/adm_login").setViewName("login_adm");
        registry.addViewController("/user/home").setViewName("user_dashboard");
        registry.addViewController("/adm/home").setViewName("admin_dashboard");
        registry.addViewController("/register").setViewName("register_user");
    }
}
