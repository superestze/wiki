package com.jiawa.wiki.config;

// import com.jiawa.wiki.interceptor.ActionInterceptor;

import com.jiawa.wiki.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LoginInterceptor loginInterceptor;

    // @Resource
    // ActionInterceptor actionInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/test/**",
                        "/redis/**",
                        "/user/login",
                        "/category/all",
                        "/ebook/list",
                        "/doc/all/**",
                        "/doc/vote/**",
                        "/doc/find-content/**",
                        "/ebook-snapshot/**"
                );

        // 为什么要做后端的拦截呢？ 要是不使用拦截的话， 所有的接口都类似在裸奔，
        // 别人可以试用工具任意的调用你的接口， 这样给服务器造成的压力就会非常的大
        // 一旦有人作恶， 自己的整个系统可能都会崩溃， 因此， 是有必要做登录校验的

        // registry.addInterceptor(actionInterceptor)
        //         .addPathPatterns(
        //                 "/*/save",
        //                 "/*/delete/**",
        //                 "/*/reset-password");
    }
}
