package com.spring.mvcproject.database.mybatis.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.mvcproject.database.mybatis.entity.Pet;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class PetDetailResponse {

    @JsonProperty("pet_no")
    private Long id;

    @JsonProperty("name")
    private String petName;

    @JsonProperty("birth_year")
    private int birthYear;

    private boolean injection;

    // Pet 엔터티 클래스(DB에서 조회한 생데이터)를 자기자신 DTO로 변환하는 편의 메서드
    public static PetDetailResponse from(Pet pet) {
        return PetDetailResponse.builder()
                .petName(pet.getPetName())
                .id(pet.getId())
                .injection(pet.isInjection())
                .birthYear(LocalDate.now().getYear() - pet.getPetAge() + 1)
                .build();
    }

}
