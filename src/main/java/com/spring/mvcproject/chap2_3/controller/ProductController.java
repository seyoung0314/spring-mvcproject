package com.spring.mvcproject.chap2_3.controller;

import com.spring.mvcproject.chap2_3.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    // 가상의 메모리 상품 저장소
    private Map<Long, Product> productStore = new HashMap<>();

    // 상품의 id를 자동 생성
    private long nextId = 1;

    public ProductController() {
        productStore.put(1L,new Product(nextId,"에어컨",1000000));
        nextId++;
        productStore.put(3L,new Product(nextId,"공기청정기",2000000));
        nextId++;
        productStore.put(2L,new Product(nextId,"세탁기",1500000));
        nextId++;
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
    @GetMapping("/{id}")
    public Product getProduct(
//        @PathVariable("id") Long id
            @PathVariable Long id // 같으면 이렇게 생략가능
    ) {
        System.out.printf("/products/%s : GET 요청이 들어옴!\n".formatted(id));
        System.out.println("id = " + id);

        Product product = productStore.get(id);
        return product;
    }

    //전체 게시물 조회요철 처리
    @GetMapping  // 클래스 상단에 경로 표시
    public List<Product> list( ) {
//        List<Product> products = new ArrayList<>();
//        for (Long id : productStore.keySet()) {
//            Product product = productStore.get(id);
//            products.add(product);
//        }
        return productStore.values().stream()
                .collect(Collectors.toList());
    }


    // 상품 정보 생성 요청
//    @RequestMapping(value = "",method = RequestMethod.POST)
//    @PostMapping
//    public String create(){
//        // 상품객체를 생성해서 맵에 저장
//        Product newProduct = new Product();
//        newProduct.setId(nextId);
//        productStore.put(nextId++,newProduct);
//
//        return "상품이 생성되었습니다. - " + newProduct;
//    }

    // 상품 정보 생성 요청
//    @RequestMapping(value = "", method = RequestMethod.POST)
    @PostMapping
    public String create(
            @RequestParam String name,
            @RequestParam int price
    ) {

        // 상품객체를 생성해서 맵에 저장
        Product newProduct = new Product(nextId, name, price);
//        newProduct.setId(nextId);
//        newProduct.setName(name);
//        newProduct.setPrice(price);
        productStore.put(nextId++, newProduct);

        return "상품이 생성되었습니다! - " + newProduct;
    }

    // 상품 수정 요청
    @PutMapping("/{id}")
    public String updateProduct(
            @PathVariable Long id
            ,@RequestParam("name") String newName
            ,@RequestParam("price") int newPrice
    ){
        // 비지니스 로직
        Product foundProduct = productStore.get(id);
        foundProduct.setName(newName);
        foundProduct.setId(id);
        foundProduct.setPrice(newPrice);

        return "상품이 수정되었습니다! - " + foundProduct;
    }

    // 상품 삭제 요청
    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable Long id
    ) {
        productStore.remove(id);
        return id + "번 상품이 삭제되었습니다.";
    }



//    curl.exe -X POST "http://localhost:9000/products" -d "name=이어폰&price=300000"

}
