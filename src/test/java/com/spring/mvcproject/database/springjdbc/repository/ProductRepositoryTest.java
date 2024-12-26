package com.spring.mvcproject.database.springjdbc.repository;

import com.spring.mvcproject.database.springjdbc.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void saveTest() {

        Product product = Product.builder()
                .name("오징어땅콩")
                .description("과자")
                .price(2300)
                .seller("슈퍼")
                .stockQuantity(3)
                .build();
//        Product product = new Product(null,"포테토칩",2200,0,"과자","이마트","Sold-Out",null);

//        product.setName("새우깡2");
//        product.setPrice(1700);
//        product.setSeller("슈퍼");
//        product.setDescription("과자");
//        product.setStockQuantity(5);

        productRepository.save(product);
    }

    @Test
    void deleteTest() {
        Long id = 3L;

        productRepository.deleteByid(id);
    }

    @Test
    void updatePriceTest() {
        Long id = 4L;
        int newPrice = 2000;
        productRepository.updatePrice(id,newPrice);
    }

    @Test
    void findAllTest(){
        List<Product> all = productRepository.findAll3();
        System.out.println("all = " + all);
    }

    @Test
    void findByIdTest(){
        Product p = productRepository.findById2(2L);
        System.out.println("p = " + p);
    }
}