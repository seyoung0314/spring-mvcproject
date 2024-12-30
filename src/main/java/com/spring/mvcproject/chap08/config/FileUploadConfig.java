package com.spring.mvcproject.chap08.config;


import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

//파일 업로드 관련 설정 클래스
@Getter @Setter
@Configuration
public class FileUploadConfig {

    // app.yml에 있는 설정 값을 가져 옴
    @Value("${file.upload.location}")
    private String location;    //파일을 저장할 루트 디렌토리

    // 서버가 실행되면 해당 디렉토리 생성
    @PostConstruct  // 스프링이 시작되면 자동으로 실행될 코드
    public void init(){
        File directory = new File(location);
        if(!directory.exists()){
            directory.mkdirs();
        }
    }
}
