package com.contrabass.requestmapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
//PSA 스프링 웹 MVC에서 디스패쳐 서블릿을 관리해줘서 서블릿에 의존하지 않고 애플리케이션을 개발할 수 있게 된다.