package com.spring.mvcproject.chap2_3.controller;

import com.spring.mvcproject.chap2_3.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController {

    // 가상의 메모리 상품 저장소
    private Map<Long, Product> productStore = new HashMap<>();

    public ProductController() {
        productStore.put(1L,new Product(1L,"에어컨",1000000));
        productStore.put(2L,new Product(2L,"세탁기",1500000));
        productStore.put(3L,new Product(3L,"공기청정기",2000000));
    }

    // 특정 상품을 조회 : GET
    // -----옛날 방식----- String 으로만 옴
//    @GetMapping("/products")
//    public String getProduct(HttpServletRequest req){
//        String id = req.getParameter("id");
//        String price = req.getParameter("price");
//        System.out.printf("/products?id=%s : GET 요청이 들어옴!\n".formatted(id));
//        System.out.println("id = " + id);
//        System.out.println("price = " + price);
//        return "";
//    }

    //========================================================================

    //  /products?id=9&price=1000
//    @GetMapping("/products")
//    public String getProduct(
//            @RequestParam("id") Long identifier

    /// /            ,@RequestParam int price
//            ,int price // 변수명과 파라미터명이 같으면 이렇게 생략가능
//    ){
//        System.out.printf("/products?id=%s : GET 요청이 들어옴!\n".formatted(identifier));
//        System.out.println("identifier = " + identifier);
//        System.out.println("price = " + price);
//        return "";
//    }

    //========================================================================

//    //  /products?id=9&price=1000
//    @GetMapping("/products")
//    public String getProduct(
//            @RequestParam("id") Long identifier
//            ,@RequestParam(required = false, defaultValue = "1000") int price
//    ){
//        System.out.printf("/products?id=%s : GET 요청이 들어옴!\n".formatted(identifier));
//        System.out.println("identifier = " + identifier);
//        System.out.println("price = " + price);
//        System.out.println("price = " + price);
//        return "";
//    }

//  /products/9
    @GetMapping("/products/{id}")
    @ResponseBody   // json 응답
    public Product getProduct(
//        @PathVariable("id") Long id
            @PathVariable Long id // 같으면 이렇게 생략가능
    ) {
        System.out.printf("/products/%s : GET 요청이 들어옴!\n".formatted(id));
        System.out.println("id = " + id);

        Product product = productStore.get(id);
        return product;
    }

}
