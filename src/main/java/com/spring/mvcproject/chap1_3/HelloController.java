package com.spring.mvcproject.chap1_3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Controller : 클라이언트의 요청을 받아 로직을 수행하는 역할
@Controller // DispatcherServlet이 이 객체를 탐색해서 연결해줌
@RequestMapping("/chap01")
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody   //JSON응답
    public String hello(){
        System.out.println("hello spring mvc world!");
        return "졸려";
    }

    @RequestMapping("/bye")
    public String bye(){
        System.out.println("bye spring mvc world!");
        return "";
    }
}
