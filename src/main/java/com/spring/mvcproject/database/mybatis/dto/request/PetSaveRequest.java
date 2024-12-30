package com.spring.mvcproject.database.mybatis.dto.request;


import com.spring.mvcproject.database.mybatis.entity.Pet;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class PetSaveRequest {

    @NotBlank(message = "애완동물 이름은 필수입니다.")
    private String PetName;

    // notblank 는 string 에 사용
    @NotNull(message = "애완동물 나이은 필수입니다.")
    @Min(value = 0)
    @Max(value = 100)
    private int petAge;

    private  boolean injection;

    public Pet toEntity(){
        return Pet.builder()
                .petAge(this.petAge)
                .petName(this.PetName)
                .injection(this.injection)
                .build();
    }
}
