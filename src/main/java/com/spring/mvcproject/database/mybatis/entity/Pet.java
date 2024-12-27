package com.spring.mvcproject.database.mybatis.entity;


import lombok.*;

/*
create table tbl_pet(
	id bigint auto_increment primary key
    ,pet_name varchar(50)
    ,pet_age int
    ,injection boolean
);
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Builder
public class Pet {
    private Long id;
    private String petName;
    private int petAge;
    private boolean injection;
    private String status;
}
