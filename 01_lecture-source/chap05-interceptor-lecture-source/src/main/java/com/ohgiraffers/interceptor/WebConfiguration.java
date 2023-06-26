package com.ohgiraffers.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /*
    WebMvcConfigurer는 web에 관련된 설정들을 위해 여러 method를 overriding 해놓음
    interceptor를 등록하기 위해서는 addInterceptor를 overriding
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new StopwatchInterceptor(new MenuService()))
                .addPathPatterns("/*")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/error/**");
    }
}

