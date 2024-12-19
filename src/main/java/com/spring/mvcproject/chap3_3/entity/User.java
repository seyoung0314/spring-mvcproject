package com.spring.mvcproject.chap3_3.entity;


import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    private Long id;
    private String name;
    private int age;
}
