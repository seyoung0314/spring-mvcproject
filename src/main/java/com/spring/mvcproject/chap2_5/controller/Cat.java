package com.spring.mvcproject.chap2_5.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
public class Cat {
    private String name;
    private int age;
    private boolean injection;

}
