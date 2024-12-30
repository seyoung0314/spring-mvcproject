package com.spring.mvcproject.chap6_3.entity;


import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Member {
    private String account;
    private String password;
    private String nickname;
}
