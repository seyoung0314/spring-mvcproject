package com.spring.mvcproject.chap6_3.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MemberCreateRequest {
    @Email(message = "계정은 이메일형식으로 ")
    @NotBlank(message = "계정은 필수입니다.")
    private  String account;

    @NotBlank(message = "비밀번호는 필수")
    @Min(value = 8, message = "8자리 이상")
    private  String password;
    private  String nickname;
}
