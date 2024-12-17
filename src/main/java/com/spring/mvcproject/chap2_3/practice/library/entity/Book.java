package com.spring.mvcproject.chap2_3.practice.library.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter @Getter
@ToString
public class Book {
    private String title;
    private String author;
    private int price;
}
