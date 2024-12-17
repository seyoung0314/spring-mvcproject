package com.spring.mvcproject.chap2_3.practice.q1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//@RestController : @ResponseBody 생략가능
// 그냥 @Controller 로 하면 받을 떄  @ResponseBody 붙여줘야함
@RestController
@RequestMapping("/api/v1")
public class GreetingController2_3 {


    // ================ Q1

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Spring MVC!";
    }

    // ================ Q2

    @GetMapping("/products/{id}")
    public String welcome(
            // 경로에 {} 가 사용됬을 때 @PathVariable 사용
            @PathVariable("id") String productId
    ) {
        return "Product ID: " + productId;
    }

    // ================ Q3

    @GetMapping("/books2_3")
    public String getBooks(
            @RequestParam("author") String author
            , @RequestParam("genre") String genre
    ) {
        return "Author: " + author + ", Genre: " + genre;
    }

    // ================ Q4

    @GetMapping("/search")
    public String search(
            @RequestParam("query") String query
            , @RequestParam(required = false, defaultValue = "1") int page
    ) {
        return "Query: " + query + ", Page: " + page;
    }

    // ================ Q5

    //http://localhost:9000/api/v1/info/userinfo?name=name
    @GetMapping("/info/{userId}")
    public String getInfo(
//            @PathVariable("userId") String userId
            @PathVariable String userId
            ,@RequestParam("name") String name
            ) {
        return "User Info: " + userId + ", name: "+name;
    }
}

