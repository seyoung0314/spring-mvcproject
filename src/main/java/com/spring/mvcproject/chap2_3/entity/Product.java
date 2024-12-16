package com.spring.mvcproject.chap2_3.entity;

import lombok.*;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private int price;
}
