package com.spring.mvcproject.chap08.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 로컬에 저장된 파일에 접근하는 설정
@Configuration
@RequiredArgsConstructor
public class WebResourceConfig implements WebMvcConfigurer {

    private final FileUploadConfig fileUploadConfig;

    //리소스 url 매핑

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // http://localhost:9000/uploads/cat.jpeg 로 요청 시
        // 실제 로컬에 저장된 파일을 꺼내줌
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:"+fileUploadConfig.getLocation());

        System.out.println("==============================");
        System.out.println(fileUploadConfig.getLocation());
    }

}
