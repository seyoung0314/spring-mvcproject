package com.spring.mvcproject.chap2_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BasicController {


    // curl.exe -X GET "http://localhost:9000/chap2-2/hello"
    //GET요청 (URL호출은 무조건 GET요청)
    @RequestMapping(
            value = "/chap2-2/hello"
            , method = RequestMethod.POST)
    @GetMapping("/chap2-2/hello")
//    @PostMapping("/chap2-2/hello")
    @ResponseBody
    public String hello() {
        System.out.println("Get 요청이 들어옴");
        return "hello spring";
    }

    // JSP응답
    @GetMapping("/chap2-2/getjsp")
//    @ResponseBody // jsp응답이 아닌 json응답으로 바뀜
    public String getJsp(){
        // jsp 파일로 포워딩
        return "hello";
    }
}
