package com.spring.mvcproject.database.mybatis.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.mvcproject.database.mybatis.entity.Pet;
import lombok.*;

import java.time.LocalDate;

// ResponseDto : 데이터 조회 시 사용
// 클라이언트가 요구한 스펙대로 데이터를 정제
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PetResponse {

    @JsonProperty("pet_no")
    private Long id;

    private String name;
    private int age;
    private int birth;

    public static PetResponse from(Pet pet) {
        PetResponse res = new PetResponse();
        res.id = pet.getId();
        res.name = pet.getPetName();
        res.age = pet.getPetAge();
        res.birth = LocalDate.now().getYear() - res.age + 1;
        return res;
    }

}
