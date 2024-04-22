package com.huneth.hams.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * 사용자 DTO
 * 유효성 관련 Annotaion을 작성한다.
 */

@Data
public class UserDto {

    private int id;

    // @NotNull // null 체크
    // @NotEmpty // null, "" 체크
    @NotBlank // null, "", " " 체크
    private String username;

    // @NotBlank
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{8,20}", 
            message = "비밀번호는 영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank
    private String passwordChk;

    @NotBlank
    private String memberName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @Email
    private String email;

    private String affiliation; //소속

    private String licenseNumber; //면허번호

    private String occupation; //직업

}

