package com.spring.mvcproject.chap2_5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class ResponseController {
    ///  페이지 라우팅 - 특정 뷰( jsp, thymeleaf)를 포워딩 시켜주는 것

    @GetMapping("/pet")
    public String pet() {
        return "pet";
    }

    @GetMapping("/show/html")
    @ResponseBody // 페이지 라우팅을 하지 않고 순수 리턴데이터를 클라이언트에게 전달
    public String html() {
        return """
                <html>
                <body>
                <h1>텍스트 </h1>
                </body>
                </html>
                """;
    }

    // 텍스트 응답
    @GetMapping("/show/text")
    @ResponseBody
    public String text() {
        return "하이 텍스트";
    }

    //json응답
    @GetMapping("/json/hobbies")
    @ResponseBody
    public List<String> hobbies() {
        return List.of("수영", "농구", "낮잠");
    }

    //json객체 응답
    @GetMapping("/json/my-cat")
    @ResponseBody
    public Map<String, Object> myCat(){
        return Map.of(
                "name","야옹"
                ,"age",3
                ,"injection",false
        );
    }

    @GetMapping("/json/my-cat2")
    @ResponseBody
    public Cat myCat2(){
        return new Cat("야옹",5,true);
    }

    @GetMapping("/json/my-cats")
    @ResponseBody
    public List<Cat> cats(){
        return List.of(
                new Cat("야옹",5,true),
                new Cat("야옹2",6,true),
                new Cat("야옹3",2,false),
                new Cat("야옹4",8,true)
        );
    }
}
