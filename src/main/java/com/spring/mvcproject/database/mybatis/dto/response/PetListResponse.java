package com.spring.mvcproject.database.mybatis.dto.response;


import lombok.*;

import java.util.List;


@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PetListResponse {
    private  int totalCount;
    private List<PetResponse> petList;
}
